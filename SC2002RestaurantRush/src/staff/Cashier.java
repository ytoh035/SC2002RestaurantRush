package staff;

import domain.Customer;
import domain.Order;
import domain.Restaurant;
import enums.CustomerState;
import java.util.List;
import java.util.Optional;

/** Automatically takes at most one payment per turn, oldest READY_TO_PAY customer first. */
public class Cashier extends Staff {

    public Cashier(String name) {
        super(name);
    }

    public Optional<Order> collectPayment(Restaurant r) {
        if (hasActed()) return Optional.empty();
        List<Customer> ready = r.getReadyToPayOldestFirst();
        if (ready.isEmpty()) return Optional.empty();

        Customer paying = ready.get(0);
        Optional<Order> active = r.getActiveOrderOf(paying);
        if (active.isEmpty()) return Optional.empty();

        Order o = active.get();
        r.getStats().recordPayment(o.getPriceCents(), paying.getSatisfaction());
        o.markPaid();
        paying.setState(CustomerState.PAID);
        r.getTableOf(paying).ifPresent(t -> t.free());
        markActed();
        return Optional.of(o);
    }
}
