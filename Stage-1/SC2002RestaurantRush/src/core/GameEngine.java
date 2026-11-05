package core;

import domain.Customer;
import domain.Order;
import domain.Restaurant;
import domain.Table;
import enums.CustomerState;
import enums.GameResult;
import enums.OrderStatus;
import enums.WaiterTask;
import staff.Cashier;
import staff.Chef;
import staff.Waiter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Turn controller. Owns the six-step turn sequence from the brief. Contains no
 * input or output: the UI calls startTurn, applies the manager's decisions,
 * then calls finishTurn and displays the returned TurnSummary.
 */
public class GameEngine {
    private final Restaurant restaurant;
    private final Waiter waiter;
    private final Chef chef;
    private final Cashier cashier;
    private final ArrivalSchedule schedule;
    private final GameConfig config;
    private int currentTurn = 1;

    public GameEngine(Restaurant restaurant, Waiter waiter, Chef chef, Cashier cashier,
                      ArrivalSchedule schedule, GameConfig config) {
        this.restaurant = restaurant;
        this.waiter = waiter;
        this.chef = chef;
        this.cashier = cashier;
        this.schedule = schedule;
        this.config = config;
    }

    /** Step 1: arrivals, and reset the staff for the new turn. */
    public void startTurn() {
        schedule.customerArrivingOn(currentTurn).ifPresent(restaurant::addToQueue);
        waiter.resetTurn();
        chef.resetTurn();
        cashier.resetTurn();
    }

    /** Waiter tasks that have at least one valid target right now. WAIT is always available. */
    public List<WaiterTask> availableWaiterTasks() {
        List<WaiterTask> tasks = new ArrayList<>();
        if (!restaurant.getQueue().isEmpty() && restaurant.findFreeTable().isPresent()) tasks.add(WaiterTask.SEAT);
        if (!restaurant.getCustomersInState(CustomerState.SEATED).isEmpty()) tasks.add(WaiterTask.TAKE_ORDER);
        if (!restaurant.getOrdersWithStatus(OrderStatus.READY).isEmpty()) tasks.add(WaiterTask.SERVE);
        tasks.add(WaiterTask.WAIT);
        return tasks;
    }

    /** Orders the Chef could advance this turn. */
    public List<Order> ordersAwaitingPreparation() {
        return restaurant.getOrdersWithStatus(OrderStatus.PLACED);
    }

    /**
     * Steps 2 and 3 (Waiter part). targetId is a customer id for SEAT and TAKE_ORDER,
     * an order id for SERVE, and ignored for WAIT. A failed result consumes no task.
     */
    public ActionResult applyWaiterAction(WaiterTask task, int targetId) {
        switch (task) {
            case SEAT: {
                Optional<Customer> c = restaurant.getCustomerById(targetId);
                if (c.isEmpty()) return ActionResult.fail("No customer with id " + targetId + ".");
                Optional<Table> t = restaurant.findFreeTable();
                if (t.isEmpty()) return ActionResult.fail("No free table.");
                return waiter.seat(c.get(), t.get(), restaurant, currentTurn);
            }
            case TAKE_ORDER: {
                Optional<Customer> c = restaurant.getCustomerById(targetId);
                if (c.isEmpty()) return ActionResult.fail("No customer with id " + targetId + ".");
                return waiter.takeOrder(c.get(), restaurant);
            }
            case SERVE: {
                Optional<Order> o = restaurant.getOrderById(targetId);
                if (o.isEmpty()) return ActionResult.fail("No order with id " + targetId + ".");
                return waiter.serve(o.get(), restaurant, currentTurn);
            }
            default:
                return ActionResult.ok("Waiter waits.");
        }
    }

    /** Steps 2 and 3 (Chef part). Pass an order id, or a non-positive id to wait. */
    public ActionResult applyChefAction(int orderId) {
        if (orderId <= 0) return ActionResult.ok("Chef waits.");
        Optional<Order> o = restaurant.getOrderById(orderId);
        if (o.isEmpty()) return ActionResult.fail("No order with id " + orderId + ".");
        return chef.prepare(o.get());
    }

    /** Steps 4 and 5, then advance the turn counter. */
    public TurnSummary finishTurn() {
        List<String> events = new ArrayList<>();

        // Step 4a: payment first, so a customer who becomes READY_TO_PAY this turn pays next turn.
        cashier.collectPayment(restaurant).ifPresent(o -> events.add(
            "Cashier collects " + util.Money.format(o.getPriceCents()) + " from Customer "
            + o.getCustomer().getId() + " (satisfaction " + o.getCustomer().getSatisfaction() + " recorded). Table freed."));

        // Step 4b: a customer served on an earlier turn has now eaten for one full turn.
        for (Customer c : restaurant.getCustomers()) {
            if (c.getState() == CustomerState.EATING && c.getServedTurn() < currentTurn) {
                c.setState(CustomerState.READY_TO_PAY);
                c.setReadyToPayTurn(currentTurn);
                events.add("Customer " + c.getId() + " finishes eating and is READY_TO_PAY.");
            }
        }

        // Step 5: waiting loss and abandonment for everyone not yet served.
        for (Customer c : new ArrayList<>(restaurant.getCustomers())) {
            c.applyWaitingLoss();
            if (c.isAbandoned()) {
                restaurant.removeAbandoned(c);
                events.add("Customer " + c.getId() + " leaves unhappy without paying. Order cancelled, table freed.");
            }
        }

        GameStats s = restaurant.getStats();
        TurnSummary summary = new TurnSummary(currentTurn, s.getServedCount(), s.getAbandonedCount(),
            s.getRevenueCents(), s.getAverageSatisfaction(), events);
        currentTurn++;
        return summary;
    }

    /** The game always runs exactly maxTurns turns. */
    public boolean isGameOver() {
        return currentTurn > config.getMaxTurns();
    }

    /** Step 6. */
    public GameResult evaluateResult() {
        return restaurant.getStats().evaluate(config);
    }

    public int getCurrentTurn() { return currentTurn; }
    public Restaurant getRestaurant() { return restaurant; }
    public GameConfig getConfig() { return config; }
}
