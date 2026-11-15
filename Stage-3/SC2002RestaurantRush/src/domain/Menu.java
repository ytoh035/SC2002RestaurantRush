package domain;

import java.util.ArrayList;
import java.util.List;
import util.Money;

public class Menu {
    private List<MenuItem> items;
    private List<ComboMeal> combos;   // Stage 3

    public Menu() {
        items = new ArrayList<>();
        // Using your utility to safely store the integer cents!
        items.add(new MenuItem("Salad", Money.fromDollars(9.00), 1));
        items.add(new MenuItem("Burger", Money.fromDollars(15.00), 1));
        items.add(new MenuItem("Pasta", Money.fromDollars(18.00), 2));

        // Stage 3: combos are built from the existing items (composition).
        MenuItem salad = items.get(0);
        MenuItem burger = items.get(1);
        MenuItem pasta = items.get(2);
        combos = new ArrayList<>();
        combos.add(new ComboMeal(salad, pasta));    // $24.30, 3 units
        combos.add(new ComboMeal(salad, burger));   // $21.60, 2 units
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

    /** Stage 3 combos. Kept apart from single items, so getMostExpensive() still means the best single dish. */
    public ComboMeal getCombo(int index) {
        return combos.get(index);
    }

    public int comboCount() {
        return combos.size();
    }

    public List<ComboMeal> getCombos() {
        return new ArrayList<>(combos);
    }

    /** Highest price wins; on a tie the item with more preparation units wins. */
    public MenuItem getMostExpensive() {
        MenuItem best = items.get(0);
        for (MenuItem m : items) {
            if (m.getPriceCents() > best.getPriceCents()
                || (m.getPriceCents() == best.getPriceCents() && m.getPrepUnits() > best.getPrepUnits())) {
                best = m;
            }
        }
        return best;
    }
}