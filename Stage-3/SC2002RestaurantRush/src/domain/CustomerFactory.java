package domain;

import enums.CustomerType;

/** The only place that knows which concrete Customer class belongs to which CustomerType. */
public final class CustomerFactory {
    private CustomerFactory() {
    }

    public static Customer create(CustomerType type, int id) {
        switch (type) {
            case VIP:
                return new VIPCustomer(id);
            case CRITIC:
                return new CriticCustomer(id);
            case REGULAR:
            default:
                return new RegularCustomer(id);
        }
    }
}
