package staff;

import core.ActionResult;
import domain.Order;
import enums.OrderStatus;

/** One preparation unit per turn on a PLACED order. */
public class Chef extends Staff {

    public Chef(String name) {
        super(name);
    }

    public ActionResult prepare(Order o) {
        if (hasActed()) return ActionResult.fail("Chef has already acted this turn.");
        if (o.getStatus() != OrderStatus.PLACED) return ActionResult.fail("Order #" + o.getOrderId() + " is " + o.getStatus() + " and needs no preparation.");

        o.advance();
        markActed();
        String progress = o.getUnitsDone() + "/" + o.getItem().getPrepUnits();
        return ActionResult.ok("Chef works on Order #" + o.getOrderId() + " (" + progress + " units)"
            + (o.isReady() ? ". It is now READY." : "."));
    }
}
