package core;

import enums.CustomerType;
import java.util.Arrays;
import java.util.List;

/** Base setting from Section 2.1 of the brief. Other difficulty settings could subclass or construct differently. */
public class GameConfig {
    private final int maxTurns = 18;
    private final int tableCount = 2;
    private final List<Integer> arrivalTurns = Arrays.asList(1, 4, 7, 10, 13, 16);
    private final List<CustomerType> arrivalTypes;
    private final int targetPaid = 4;
    private final int targetRevenueCents = 4500;
    private final int targetSatisfaction = 60;

    /** Stage 2 base setting: customers 2 and 4 are a VIP and a Critic; there are still six arrivals. */
    public GameConfig() {
        this(Arrays.asList(CustomerType.REGULAR, CustomerType.VIP, CustomerType.REGULAR,
                           CustomerType.CRITIC, CustomerType.REGULAR, CustomerType.REGULAR));
    }

    public GameConfig(List<CustomerType> arrivalTypes) {
        this.arrivalTypes = arrivalTypes;
    }

    /** Six Regular arrivals (no VIP or Critic). Optional setting, run with the "regular" argument. */
    public static GameConfig regularOnly() {
        return new GameConfig(Arrays.asList(CustomerType.REGULAR, CustomerType.REGULAR, CustomerType.REGULAR,
                                            CustomerType.REGULAR, CustomerType.REGULAR, CustomerType.REGULAR));
    }

    public int getMaxTurns() { return maxTurns; }
    public int getTableCount() { return tableCount; }
    public List<Integer> getArrivalTurns() { return arrivalTurns; }
    public List<CustomerType> getArrivalTypes() { return arrivalTypes; }
    public int getTargetPaid() { return targetPaid; }
    public int getTargetRevenueCents() { return targetRevenueCents; }
    public int getTargetSatisfaction() { return targetSatisfaction; }
}
