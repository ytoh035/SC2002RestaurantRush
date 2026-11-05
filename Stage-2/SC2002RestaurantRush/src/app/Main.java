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
        GameConfig config = new GameConfig();
        Menu menu = new Menu();
        Restaurant restaurant = new Restaurant(config.getTableCount(), menu);

        Waiter waiter = new Waiter("Waiter");
        Chef chef = new Chef("Chef");
        Cashier cashier = new Cashier("Cashier");
        ArrivalSchedule schedule = new ArrivalSchedule(config.getArrivalTurns());

        GameEngine engine = new GameEngine(restaurant, waiter, chef, cashier, schedule, config);
        new RestaurantCLI(engine).run();
    }
}
