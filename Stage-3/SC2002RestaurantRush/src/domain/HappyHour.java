package domain;

/**
 * Stage 3. A pricing policy the manager may switch on once per game, at the start of a turn.
 * It lasts for that turn and the next.
 *
 * While active:
 *  - orders taken get 20% off (applied after any customer discount such as VIP);
 *  - customers still waiting for a table or food recover 15 satisfaction at turn start (max 100).
 * The price is locked when the order is taken, so it never changes orders already placed.
 */
public class HappyHour {
    public static final int DURATION_TURNS = 2;
    public static final int RECOVERY = 15;
    private static final int PERCENT_PAID = 80;   // 20% off

    private boolean used = false;
    private int turnsRemaining = 0;

    public boolean canActivate() {
        return !used;
    }

    /** Switches Happy Hour on for this turn and the next. Does nothing if it was already used. */
    public void activate() {
        if (!used) {
            used = true;
            turnsRemaining = DURATION_TURNS;
        }
    }

    public boolean isActive() {
        return turnsRemaining > 0;
    }

    /** Turns left including the current one: 2 on the first turn, 1 on the second. */
    public int getTurnsRemaining() {
        return turnsRemaining;
    }

    public boolean hasBeenUsed() {
        return used;
    }

    /** 20% off while active, rounded to the nearest cent; unchanged otherwise. */
    public int applyDiscount(int priceCents) {
        if (!isActive()) {
            return priceCents;
        }
        return (priceCents * PERCENT_PAID + 50) / 100;
    }

    /** Called once at the end of every turn. Returns true on the turn Happy Hour finishes. */
    public boolean endTurn() {
        if (!isActive()) {
            return false;
        }
        turnsRemaining--;
        return turnsRemaining == 0;
    }
}
