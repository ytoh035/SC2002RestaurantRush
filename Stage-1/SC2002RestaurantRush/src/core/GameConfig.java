package core;

import java.util.Arrays;
import java.util.List;

/** Base setting from Section 2.1 of the brief. Other difficulty settings could subclass or construct differently. */
public class GameConfig {
    private final int maxTurns = 18;
    private final int tableCount = 2;
    private final List<Integer> arrivalTurns = Arrays.asList(1, 4, 7, 10, 13, 16);
    private final int targetPaid = 4;
    private final int targetRevenueCents = 4500;
    private final int targetSatisfaction = 60;

    public int getMaxTurns() { return maxTurns; }
    public int getTableCount() { return tableCount; }
    public List<Integer> getArrivalTurns() { return arrivalTurns; }
    public int getTargetPaid() { return targetPaid; }
    public int getTargetRevenueCents() { return targetRevenueCents; }
    public int getTargetSatisfaction() { return targetSatisfaction; }
}
