package domain;

import enums.OrderStatus;

public class Order {
    private final int orderId;
    private final Customer customer;
    private final MenuItem item;
    private final int priceCents;   // locked when the order is taken
    private final boolean happyHourPrice;
    private int unitsDone;
    private OrderStatus status;

    /**
     * The bill is worked out once, here, and never changes afterwards:
     * the customer's own discount first (e.g. VIP 10% off), then Happy Hour (20% off) if it is active.
     * Example: VIP Burger during Happy Hour = 1500 x 0.9 x 0.8 = 1080 cents ($10.80).
     */
    public Order(int orderId, Customer customer, MenuItem item, HappyHour happyHour) {
        this.orderId = orderId;
        this.customer = customer;
        this.item = item;
        int customerPrice = customer.applyDiscount(item.getPriceCents());
        this.happyHourPrice = happyHour.isActive();
        this.priceCents = happyHour.applyDiscount(customerPrice);
        this.unitsDone = 0;
        this.status = OrderStatus.PLACED;
    }

    /** One preparation unit of work by the Chef. Becomes READY on the last unit. */
    public void advance() {
        if (status == OrderStatus.PLACED && unitsDone < item.getPrepUnits()) {
            unitsDone++;
            if (unitsDone == item.getPrepUnits()) {
                status = OrderStatus.READY;
            }
        }
    }

    public boolean isReady() { return status == OrderStatus.READY; }
    public boolean isUnfinished() { return status == OrderStatus.PLACED || status == OrderStatus.READY; }
    public boolean isActive() { return status != OrderStatus.PAID && status != OrderStatus.CANCELLED; }
    public void markServed() { status = OrderStatus.SERVED; }
    public void markPaid() { status = OrderStatus.PAID; }
    public void cancel() { status = OrderStatus.CANCELLED; }

    public int getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public MenuItem getItem() { return item; }
    public int getPriceCents() { return priceCents; }
    public boolean isHappyHourPrice() { return happyHourPrice; }
    public int getUnitsDone() { return unitsDone; }
    public OrderStatus getStatus() { return status; }
}
