package staff;

import core.ActionResult;
import domain.Customer;
import domain.MenuItem;
import domain.Order;
import domain.Restaurant;
import domain.Table;
import enums.CustomerState;
import enums.OrderStatus;

/** One task per turn: seat, take an order, or serve a READY order. */
public class Waiter extends Staff {

    public Waiter(String name) {
        super(name);
    }

    public ActionResult seat(Customer c, Table t, Restaurant r, int turn) {
        if (hasActed()) return ActionResult.fail("Waiter has already acted this turn.");
        if (c.getState() != CustomerState.QUEUED) return ActionResult.fail("Customer " + c.getId() + " is not in the waiting queue.");
        if (!t.isFree()) return ActionResult.fail("Table " + t.getTableId() + " is already occupied.");

        t.seat(c);
        r.getQueue().remove(c);
        c.setState(CustomerState.SEATED);
        c.setSeatedTurn(turn);
        markActed();
        return ActionResult.ok("Customer " + c.getId() + " seated at table " + t.getTableId() + ".");
    }

    public ActionResult takeOrder(Customer c, Restaurant r) {
        if (hasActed()) return ActionResult.fail("Waiter has already acted this turn.");
        if (c.getState() != CustomerState.SEATED) return ActionResult.fail("Customer " + c.getId() + " is not seated, or has already ordered.");

        MenuItem item = c.chooseFood(r.getMenu());   // the customer chooses, never the manager
        Order o = r.createOrder(c, item);
        c.setState(CustomerState.ORDERED);
        markActed();
        return ActionResult.ok("Customer " + c.getId() + " orders " + item.getName()
            + " (Order #" + o.getOrderId() + ", " + item.getPrepUnits() + " prep unit(s)).");
    }

    public ActionResult serve(Order o, Restaurant r, int turn) {
        if (hasActed()) return ActionResult.fail("Waiter has already acted this turn.");
        if (o.getStatus() != OrderStatus.READY) return ActionResult.fail("Order #" + o.getOrderId() + " is " + o.getStatus() + ", not READY.");

        o.markServed();
        Customer c = o.getCustomer();
        c.setState(CustomerState.EATING);
        c.setServedTurn(turn);
        r.getStats().recordServed();
        markActed();
        return ActionResult.ok("Order #" + o.getOrderId() + " (" + o.getItem().getName() + ") served to Customer " + c.getId() + ".");
    }
}
