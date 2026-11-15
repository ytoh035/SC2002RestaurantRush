package domain;

import enums.CustomerState;

/**
 * A guest of the restaurant. Holds satisfaction and life-cycle state, and owns
 * the behaviour that differs between customer kinds (waiting loss, food choice,
 * discount). Subclasses override the hooks; the turn controller never checks types.
 */
public abstract class Customer {
    public static final int MAX_SATISFACTION = 100;
    /** Stage 3: every third customer (ids 3, 6, ...) orders a combo. */
    private static final int COMBO_EVERY = 3;

    private final int id;
    private int satisfaction;
    private CustomerState state;
    private int seatedTurn;
    private int servedTurn;
    private int readyToPayTurn;

    public Customer(int id) {
        this.id = id;
        this.satisfaction = MAX_SATISFACTION;
        this.state = CustomerState.QUEUED;
    }

    /** Satisfaction points lost per turn while waiting for a table or for food. */
    public abstract int waitingLoss();

    /** Short label shown in the UI, e.g. "Regular". */
    public abstract String typeName();

    /**
     * Customers choose their own food. Deterministic rule documented in README:
     * - Stage 3: every third customer (id 3, 6, ...) orders a combo,
     *   combo number (id / 3 - 1) mod number of combos, so id 3 orders
     *   Salad + Pasta and id 6 orders Salad + Burger;
     * - everyone else: menu index = (id - 1) mod menu size, so ids 1, 2, 4, 5
     *   pick Salad, Burger, Salad, Burger.
     */
    public MenuItem chooseFood(Menu menu) {
        if (menu.comboCount() > 0 && id % COMBO_EVERY == 0) {
            return menu.getCombo((id / COMBO_EVERY - 1) % menu.comboCount());
        }
        return menu.get((id - 1) % menu.size());
    }

    /** Price this customer is billed for an item at the given list price. Full price by default. */
    public int applyDiscount(int priceCents) {
        return priceCents;
    }

    /** True while the customer has not yet been served food. */
    public boolean isWaitingForService() {
        return state == CustomerState.QUEUED
            || state == CustomerState.SEATED
            || state == CustomerState.ORDERED;
    }

    /** Step 5: apply the per-turn satisfaction loss, floored at 0. */
    public void applyWaitingLoss() {
        if (isWaitingForService()) {
            satisfaction = Math.max(0, satisfaction - waitingLoss());
        }
    }

    /** Stage 3 (Happy Hour): a customer still waiting recovers points, up to the maximum of 100. */
    public void recoverSatisfaction(int points) {
        if (isWaitingForService()) {
            satisfaction = Math.min(MAX_SATISFACTION, satisfaction + points);
        }
    }

    /** A waiting customer whose satisfaction has hit 0 leaves without paying. */
    public boolean isAbandoned() {
        return isWaitingForService() && satisfaction == 0;
    }

    public int getId() { return id; }
    public int getSatisfaction() { return satisfaction; }
    public CustomerState getState() { return state; }
    public void setState(CustomerState state) { this.state = state; }
    public int getSeatedTurn() { return seatedTurn; }
    public void setSeatedTurn(int turn) { this.seatedTurn = turn; }
    public int getServedTurn() { return servedTurn; }
    public void setServedTurn(int turn) { this.servedTurn = turn; }
    public int getReadyToPayTurn() { return readyToPayTurn; }
    public void setReadyToPayTurn(int turn) { this.readyToPayTurn = turn; }
}
