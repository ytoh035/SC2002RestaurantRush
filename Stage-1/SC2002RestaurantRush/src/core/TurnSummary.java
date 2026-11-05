package core;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/** Immutable end-of-turn report built by GameEngine.finishTurn() and displayed by the UI. */
public class TurnSummary {
    private final int turnNumber;
    private final int servedCount;
    private final int unhappyDepartures;
    private final int revenueCents;
    private final Optional<Double> averageSatisfaction;
    private final List<String> events;

    public TurnSummary(int turnNumber, int servedCount, int unhappyDepartures, int revenueCents,
                       Optional<Double> averageSatisfaction, List<String> events) {
        this.turnNumber = turnNumber;
        this.servedCount = servedCount;
        this.unhappyDepartures = unhappyDepartures;
        this.revenueCents = revenueCents;
        this.averageSatisfaction = averageSatisfaction;
        this.events = Collections.unmodifiableList(events);
    }

    public int getTurnNumber() { return turnNumber; }
    public int getServedCount() { return servedCount; }
    public int getUnhappyDepartures() { return unhappyDepartures; }
    public int getRevenueCents() { return revenueCents; }
    public Optional<Double> getAverageSatisfaction() { return averageSatisfaction; }
    public List<String> getEvents() { return events; }
}

