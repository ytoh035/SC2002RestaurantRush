package staff;

import core.ActionResult;
import domain.Customer;
import domain.Restaurant;
import domain.Table;
import enums.CustomerState;

/**
 * Stage 3. The Host seats at most one queued customer per turn, in addition to the
 * Waiter's task. The Waiter can still seat customers too.
 * Both use Restaurant.seatCustomer(), so the seating rule is written only once.
 */
public class Host extends Staff {

    public Host(String name) {
        super(name);
    }

    public ActionResult seat(Customer c, Table t, Restaurant r, int turn) {
        if (hasActed()) return ActionResult.fail("Host has already seated a customer this turn.");
        if (c.getState() != CustomerState.QUEUED) return ActionResult.fail("Customer " + c.getId() + " is not in the waiting queue.");
        if (!t.isFree()) return ActionResult.fail("Table " + t.getTableId() + " is already occupied.");

        r.seatCustomer(c, t, turn);
        markActed();
        return ActionResult.ok("Host seats Customer " + c.getId() + " at table " + t.getTableId() + ".");
    }
}
