package core;

import domain.Customer;
import domain.CustomerFactory;
import enums.CustomerType;
import java.util.List;
import java.util.Optional;

/** Produces the customer scheduled to arrive on a given turn, with sequential ids. */
public class ArrivalSchedule {
    private final List<Integer> arrivalTurns;
    private final List<CustomerType> arrivalTypes;
    private int nextCustomerId = 1;

    public ArrivalSchedule(List<Integer> arrivalTurns, List<CustomerType> arrivalTypes) {
        if (arrivalTurns.size() != arrivalTypes.size()) {
            throw new IllegalArgumentException("Every arrival turn needs exactly one customer type.");
        }
        this.arrivalTurns = arrivalTurns;
        this.arrivalTypes = arrivalTypes;
    }

    public Optional<Customer> customerArrivingOn(int turn) {
        int index = arrivalTurns.indexOf(turn);
        if (index < 0) {
            return Optional.empty();
        }
        return Optional.of(CustomerFactory.create(arrivalTypes.get(index), nextCustomerId++));
    }
}
