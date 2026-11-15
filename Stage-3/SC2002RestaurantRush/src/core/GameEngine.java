package core;

import domain.Customer;
import domain.HappyHour;
import domain.Order;
import domain.Restaurant;
import domain.Table;
import enums.CustomerState;
import enums.GameResult;
import enums.OrderStatus;
import enums.WaiterTask;
import staff.Cashier;
import staff.Chef;
import staff.Host;
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
    private final Host host;
    private final Waiter waiter;
    private final Chef chef;
    private final Cashier cashier;
    private final ArrivalSchedule schedule;
    private final GameConfig config;
    private int currentTurn = 1;

    public GameEngine(Restaurant restaurant, Host host, Waiter waiter, Chef chef, Cashier cashier,
                      ArrivalSchedule schedule, GameConfig config) {
        this.restaurant = restaurant;
        this.host = host;
        this.waiter = waiter;
        this.chef = chef;
        this.cashier = cashier;
        this.schedule = schedule;
        this.config = config;
    }

    /**
     * Step 1: arrivals, reset the staff for the new turn, and (Stage 3) Happy Hour recovery
     * if Happy Hour is already running. Returns messages for the UI to show.
     */
    public List<String> startTurn() {
        List<String> events = new ArrayList<>();
        schedule.customerArrivingOn(currentTurn).ifPresent(c -> {
            restaurant.addToQueue(c);
            events.add("Customer " + c.getId() + " (" + c.typeName() + ") arrives and joins the queue.");
        });
        host.resetTurn();
        waiter.resetTurn();
        chef.resetTurn();
        cashier.resetTurn();
        if (restaurant.getHappyHour().isActive()) {
            events.addAll(applyHappyHourRecovery());
        }
        return events;
    }

    // ---------- Stage 3: Happy Hour ----------

    public boolean canActivateHappyHour() {
        return restaurant.getHappyHour().canActivate();
    }

    /** Manager switches Happy Hour on. Allowed once per game, at the start of a turn. */
    public ActionResult activateHappyHour() {
        HappyHour hh = restaurant.getHappyHour();
        if (!hh.canActivate()) return ActionResult.fail("Happy Hour can only be used once per game.");
        if (host.hasActed() || waiter.hasActed() || chef.hasActed()) {
            return ActionResult.fail("Happy Hour can only be switched on at the start of a turn.");
        }
        hh.activate();
        List<String> recovered = applyHappyHourRecovery();
        String extra = recovered.isEmpty() ? " Nobody is waiting, so nobody recovers yet." : " " + String.join(" ", recovered);
        return ActionResult.ok("Happy Hour is ON for this turn and the next (20% off new orders)." + extra);
    }

    /** Every customer still waiting for a table or food recovers 15 satisfaction (max 100). */
    private List<String> applyHappyHourRecovery() {
        List<String> events = new ArrayList<>();
        for (Customer c : restaurant.getCustomers()) {
            int before = c.getSatisfaction();
            c.recoverSatisfaction(HappyHour.RECOVERY);
            if (c.getSatisfaction() != before) {
                events.add("Happy Hour: Customer " + c.getId() + " recovers " + before + " -> " + c.getSatisfaction() + ".");
            }
        }
        return events;
    }

    // ---------- Stage 3: Host ----------

    /** True if the Host could seat someone right now. */
    public boolean hostCanSeat() {
        return !host.hasActed() && !restaurant.getQueue().isEmpty() && restaurant.findFreeTable().isPresent();
    }

    /** The Host seats one queued customer. Pass a customer id, or a non-positive id to wait. */
    public ActionResult applyHostAction(int customerId) {
        if (customerId <= 0) return ActionResult.ok("Host waits.");
        Optional<Customer> c = restaurant.getCustomerById(customerId);
        if (c.isEmpty()) return ActionResult.fail("No customer with id " + customerId + ".");
        Optional<Table> t = restaurant.findFreeTable();
        if (t.isEmpty()) return ActionResult.fail("No free table.");
        return host.seat(c.get(), t.get(), restaurant, currentTurn);
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

        // Stage 3: count down Happy Hour.
        if (restaurant.getHappyHour().endTurn()) {
            events.add("Happy Hour has ended.");
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
