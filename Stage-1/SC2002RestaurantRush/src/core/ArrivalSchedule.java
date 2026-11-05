package core;

import domain.Customer;
import domain.RegularCustomer;
import java.util.List;
import java.util.Optional;


public class ArrivalSchedule {
    private final List<Integer> arrivalTurns;
    private int nextCustomerId = 1;

    public ArrivalSchedule(List<Integer> arrivalTurns) {
        this.arrivalTurns = arrivalTurns;
    }

    public Optional<Customer> customerArrivingOn(int turn) {
        if (arrivalTurns.contains(turn)) {
            return Optional.of(new RegularCustomer(nextCustomerId++));
        }
        return Optional.empty();
    }
}
