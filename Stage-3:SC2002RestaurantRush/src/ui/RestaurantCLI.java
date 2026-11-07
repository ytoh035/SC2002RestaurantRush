package ui;

import core.ActionResult;
import core.GameConfig;
import core.GameEngine;
import core.GameStats;
import core.TurnSummary;
import domain.ComboMeal;
import domain.Customer;
import domain.HappyHour;
import domain.MenuItem;
import domain.Order;
import domain.Restaurant;
import domain.Table;
import enums.CustomerState;
import enums.GameResult;
import enums.WaiterTask;
import util.Money;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Text UI. Collects the manager's decisions and displays state. Every rule lives in
 * the engine, staff and domain classes; this class only translates between the
 * console and GameEngine.
 */
public class RestaurantCLI {
    private final Scanner scanner = new Scanner(System.in);
    private final GameEngine engine;

    public RestaurantCLI(GameEngine engine) {
        this.engine = engine;
    }

    public void run() {
        System.out.println("=== SC2002 Restaurant Rush (Stage 3) ===");
        printMenu();
        while (!engine.isGameOver()) {
            List<String> startEvents = engine.startTurn();
            printState(startEvents);
            if (engine.canActivateHappyHour()) promptHappyHour();
            promptHost();
            promptWaiter();
            promptChef();
            printSummary(engine.finishTurn());
        }
        printFinalResult(engine.evaluateResult());
    }

    // ---------- display ----------

    private void printMenu() {
        System.out.println("Menu:");
        for (MenuItem m : engine.getRestaurant().getMenu().getAll()) {
            System.out.println("  " + m.getName() + "  " + Money.format(m.getPriceCents()) + "  (" + m.getPrepUnits() + " prep unit(s))");
        }
        for (ComboMeal m : engine.getRestaurant().getMenu().getCombos()) {
            System.out.println("  " + m.getName() + "  " + Money.format(m.getPriceCents()) + "  (" + m.getPrepUnits() + " prep units)");
        }
    }

    private void printState(List<String> startEvents) {
        Restaurant r = engine.getRestaurant();
        GameStats s = r.getStats();
        System.out.println();
        System.out.println("========== Turn " + engine.getCurrentTurn() + " / " + engine.getConfig().getMaxTurns() + " ==========");
        for (String e : startEvents) System.out.println("  * " + e);
        System.out.println("Revenue: " + Money.format(s.getRevenueCents())
            + "   Paid customers: " + s.getPaidCount()
            + "   Avg satisfaction (paid): " + formatAverage(s.getAverageSatisfaction()));
        System.out.println("Happy Hour: " + happyHourText(r.getHappyHour()));

        System.out.print("Waiting queue: ");
        List<Customer> queue = r.getQueue().getAll();
        if (queue.isEmpty()) System.out.println("(empty)");
        else {
            StringBuilder sb = new StringBuilder();
            for (Customer c : queue) sb.append("C").append(c.getId()).append("(sat ").append(c.getSatisfaction()).append(") ");
            System.out.println(sb.toString().trim());
        }

        System.out.println("Tables:");
        for (Table t : r.getTables()) {
            if (t.isFree()) System.out.println("  Table " + t.getTableId() + ": free");
            else System.out.println("  Table " + t.getTableId() + ": Customer " + t.getOccupant().getId() + " (" + t.getOccupant().getState() + ")");
        }

        System.out.println("Customers:");
        boolean any = false;
        for (Customer c : r.getCustomers()) {
            if (c.getState() == CustomerState.PAID || c.getState() == CustomerState.ABANDONED) continue;
            any = true;
            StringBuilder line = new StringBuilder("  C" + c.getId() + " " + c.typeName()
                + "  state=" + c.getState() + "  sat=" + c.getSatisfaction());
            r.getActiveOrderOf(c).ifPresent(o -> line.append("  order #").append(o.getOrderId())
                .append(" ").append(o.getItem().getName())
                .append(" ").append(o.getUnitsDone()).append("/").append(o.getItem().getPrepUnits())
                .append(" units, ").append(o.getStatus())
                .append(", bill ").append(Money.format(o.getPriceCents()))
                .append(o.isHappyHourPrice() ? " (Happy Hour price)" : ""));
            System.out.println(line);
        }
        if (!any) System.out.println("  (none in the restaurant)");

        System.out.println("Staff tasks available:");
        System.out.println("  Host:   " + (engine.hostCanSeat() ? "[SEAT, WAIT]" : "[WAIT]"));
        System.out.println("  Waiter: " + engine.availableWaiterTasks());
        List<Order> prep = engine.ordersAwaitingPreparation();
        StringBuilder chefLine = new StringBuilder("  Chef:   ");
        if (prep.isEmpty()) chefLine.append("[WAIT]");
        else {
            chefLine.append("PREPARE one of ");
            for (Order o : prep) chefLine.append("#").append(o.getOrderId()).append(" ");
            chefLine.append("or WAIT");
        }
        System.out.println(chefLine);
        System.out.println("  Cashier: automatic (oldest READY_TO_PAY customer)");
    }

    private void printSummary(TurnSummary ts) {
        System.out.println("---------- End of turn " + ts.getTurnNumber() + " ----------");
        for (String e : ts.getEvents()) System.out.println("  * " + e);
        System.out.println("  Served: " + ts.getServedCount()
            + "   Unhappy departures: " + ts.getUnhappyDepartures()
            + "   Revenue: " + Money.format(ts.getRevenueCents())
            + "   Avg satisfaction (paid): " + formatAverage(ts.getAverageSatisfaction())
            + "   Turns used: " + ts.getTurnNumber() + "/" + engine.getConfig().getMaxTurns());
    }

    private void printFinalResult(GameResult result) {
        GameStats s = engine.getRestaurant().getStats();
        GameConfig cfg = engine.getConfig();
        System.out.println();
        System.out.println("================ GAME OVER ================");
        System.out.println("Paid customers:   " + s.getPaidCount() + " (target " + cfg.getTargetPaid() + ")");
        System.out.println("Revenue:          " + Money.format(s.getRevenueCents()) + " (target " + Money.format(cfg.getTargetRevenueCents()) + ")");
        System.out.println("Avg satisfaction: " + formatAverage(s.getAverageSatisfaction()) + " (target " + cfg.getTargetSatisfaction() + ")");
        System.out.println("Served: " + s.getServedCount() + "   Unhappy departures: " + s.getAbandonedCount());
        System.out.println("RESULT: " + result);
    }

    private String happyHourText(HappyHour hh) {
        if (hh.isActive()) {
            return "ACTIVE - " + hh.getTurnsRemaining() + " turn(s) left including this one (20% off new orders)";
        }
        return hh.canActivate() ? "available (once per game, lasts 2 turns)" : "used";
    }

    private String formatAverage(Optional<Double> avg) {
        return avg.map(v -> String.format("%.1f", v)).orElse("N/A");
    }

    // ---------- input ----------

    private void promptHappyHour() {
        while (true) {
            int choice = readInt("Happy Hour: 1=Switch on now  2=Not now > ");
            if (choice == 2) return;
            if (choice != 1) { System.out.println("  Please enter 1 or 2."); continue; }
            System.out.println("  " + engine.activateHappyHour().getMessage());
            return;
        }
    }

    private void promptHost() {
        while (true) {
            int choice = readInt("Host:   1=Seat a queued customer  2=Wait > ");
            ActionResult result;
            switch (choice) {
                case 1: result = engine.applyHostAction(readInt("  Customer id to seat > ")); break;
                case 2: result = engine.applyHostAction(0); break;
                default: System.out.println("  Please enter 1 or 2."); continue;
            }
            System.out.println("  " + result.getMessage());
            if (result.isSuccess()) return;
            System.out.println("  Choose again.");
        }
    }

    private void promptWaiter() {
        while (true) {
            int choice = readInt("Waiter: 1=Seat  2=Take order  3=Serve  4=Wait > ");
            ActionResult result;
            switch (choice) {
                case 1: result = engine.applyWaiterAction(WaiterTask.SEAT, readInt("  Customer id to seat > ")); break;
                case 2: result = engine.applyWaiterAction(WaiterTask.TAKE_ORDER, readInt("  Customer id to take order from > ")); break;
                case 3: result = engine.applyWaiterAction(WaiterTask.SERVE, readInt("  Order id to serve > ")); break;
                case 4: result = engine.applyWaiterAction(WaiterTask.WAIT, 0); break;
                default: System.out.println("  Please enter 1, 2, 3 or 4."); continue;
            }
            System.out.println("  " + result.getMessage());
            if (result.isSuccess()) return;
            System.out.println("  Choose again.");
        }
    }

    private void promptChef() {
        while (true) {
            int choice = readInt("Chef:   1=Prepare order  2=Wait > ");
            ActionResult result;
            switch (choice) {
                case 1: result = engine.applyChefAction(readInt("  Order id to prepare > ")); break;
                case 2: result = engine.applyChefAction(0); break;
                default: System.out.println("  Please enter 1 or 2."); continue;
            }
            System.out.println("  " + result.getMessage());
            if (result.isSuccess()) return;
            System.out.println("  Choose again.");
        }
    }

    /** Reads a whole line and parses it, so bad input never crashes the game. */
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) {
                System.out.println("\nInput ended. Exiting.");
                System.exit(0);
            }
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("  '" + line + "' is not a number.");
            }
        }
    }
}
