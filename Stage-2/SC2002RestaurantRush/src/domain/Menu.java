package domain;

import java.util.ArrayList;
import java.util.List;
import util.Money;

public class Menu {
    private List<MenuItem> items;

    public Menu() {
        items = new ArrayList<>();
        // Using your utility to safely store the integer cents!
        items.add(new MenuItem("Salad", Money.fromDollars(9.00), 1));
        items.add(new MenuItem("Burger", Money.fromDollars(15.00), 1));
        items.add(new MenuItem("Pasta", Money.fromDollars(18.00), 2));
    }

    public MenuItem get(int index) {
        return items.get(index);
    }

    public int size() {
        return items.size();
    }

    public List<MenuItem> getAll() {
        return new ArrayList<>(items); // Returns a copy to protect the original list
    }
}