package domain;

import enums.OrderStatus;

public class Order {
    private final int orderId;
    private final Customer customer;
    private final MenuItem item;
    private final int priceCents;   
    private int unitsDone;
    private OrderStatus status;

    public Order(int orderId, Customer customer, MenuItem item) {
        this.orderId = orderId;
        this.customer = customer;
        this.item = item;
        this.priceCents = customer.applyDiscount(item.getPriceCents());
        this.unitsDone = 0;
        this.status = OrderStatus.PLACED;
    }

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
    public int getUnitsDone() { return unitsDone; }
    public OrderStatus getStatus() { return status; }
}
