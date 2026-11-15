package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Stage 3. A combo made of two existing menu items.
 *
 * A ComboMeal IS a MenuItem (inheritance), so the Waiter, Chef, Order and Cashier
 * handle it exactly like any other dish without knowing it is a combo.
 * It also HAS two component items (composition).
 *
 * Preparation units = the sum of the two components.
 * Price = 90% of the components' combined price, rounded to the nearest cent.
 * Example: Salad + Pasta = (900 + 1800) x 0.9 = 2430 cents ($24.30), 3 units.
 */
public class ComboMeal extends MenuItem {
    private static final int PERCENT_PAID = 90;

    private final MenuItem first;
    private final MenuItem second;

    public ComboMeal(MenuItem first, MenuItem second) {
        super(first.getName() + " + " + second.getName() + " Combo",
              comboPrice(first, second),
              first.getPrepUnits() + second.getPrepUnits());
        this.first = first;
        this.second = second;
    }

    /** 90% of the combined price, rounded to the nearest cent using integer arithmetic. */
    private static int comboPrice(MenuItem a, MenuItem b) {
        return ((a.getPriceCents() + b.getPriceCents()) * PERCENT_PAID + 50) / 100;
    }

    /** The two dishes inside this combo (read-only). */
    public List<MenuItem> getComponents() {
        return Collections.unmodifiableList(Arrays.asList(first, second));
    }
}
