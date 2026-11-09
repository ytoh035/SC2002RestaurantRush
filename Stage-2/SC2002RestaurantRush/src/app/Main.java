package app;

import core.ArrivalSchedule;
import core.GameConfig;
import core.GameEngine;
import domain.Menu;
import domain.Restaurant;
import staff.Cashier;
import staff.Chef;
import staff.Waiter;
import ui.RestaurantCLI;

public class Main {
    public static void main(String[] args) {
        // "java -cp out app.Main regular" runs the Stage 1 all-Regular schedule (regression replays).
        boolean regularOnly = args.length > 0 && args[0].equalsIgnoreCase("regular");
        GameConfig config = regularOnly ? GameConfig.regularOnly() : new GameConfig();
        Menu menu = new Menu();
        Restaurant restaurant = new Restaurant(config.getTableCount(), menu);

        Waiter waiter = new Waiter("Waiter");
        Chef chef = new Chef("Chef");
        Cashier cashier = new Cashier("Cashier");
        ArrivalSchedule schedule = new ArrivalSchedule(config.getArrivalTurns(), config.getArrivalTypes());

        GameEngine engine = new GameEngine(restaurant, waiter, chef, cashier, schedule, config);
        new RestaurantCLI(engine).run();
    }
}
