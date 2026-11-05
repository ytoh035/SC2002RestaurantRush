package core;

import enums.GameResult;
import util.Money;
import java.util.Optional;

public class GameStats {
    private int revenueCents = 0;
    private int paidCount = 0;
    private int satisfactionTotal = 0;
    private int servedCount = 0;
    private int abandonedCount = 0;

    public void recordPayment(int cents, int satisfaction) {
        this.revenueCents += cents;
        this.satisfactionTotal += satisfaction;
        this.paidCount++;
    }

    public void recordServed() { this.servedCount++; }
    public void recordAbandon() { this.abandonedCount++; }

    public Optional<Double> getAverageSatisfaction() {
        if (paidCount == 0) return Optional.empty();
        return Optional.of((double) satisfactionTotal / paidCount);
    }

    public GameResult evaluate(GameConfig cfg) {
        double avgSat = getAverageSatisfaction().orElse(0.0);
        if (paidCount >= cfg.getTargetPaid() && 
            revenueCents >= cfg.getTargetRevenueCents() && 
            avgSat >= cfg.getTargetSatisfaction()) {
            return GameResult.VICTORY; // All 3 targets met
        }
        return GameResult.DEFEAT;
    }
    
    // Standard Getters
    public int getRevenueCents() { return revenueCents; }
    public int getPaidCount() { return paidCount; }
    public int getServedCount() { return servedCount; }
    public int getAbandonedCount() { return abandonedCount; }
}
