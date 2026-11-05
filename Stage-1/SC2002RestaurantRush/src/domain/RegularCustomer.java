package domain;

public class RegularCustomer extends Customer {
    public RegularCustomer(int id) {
        super(id);
    }

    @Override
    public int waitingLoss() {
        return 8;
    }

    @Override
    public String typeName() {
        return "Regular";
    }
}

