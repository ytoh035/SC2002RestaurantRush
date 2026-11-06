package domain;

/** VIP: loses 5 satisfaction per waiting turn and receives 10% off the bill. */
public class VIPCustomer extends Customer {
    public VIPCustomer(int id) {
        super(id);
    }

    @Override
    public int waitingLoss() {
        return 5;
    }

    /** 10% off, rounded to the nearest cent using integer arithmetic. */
    @Override
    public int applyDiscount(int priceCents) {
        return (priceCents * 90 + 50) / 100;
    }

    @Override
    public String typeName() {
        return "VIP";
    }
}
