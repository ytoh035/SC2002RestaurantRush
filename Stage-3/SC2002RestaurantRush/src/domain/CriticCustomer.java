package domain;

/**
 * Critic: loses 12 satisfaction per waiting turn and pays full price.
 * Special behaviour (documented in README): a Critic always orders the most
 * expensive dish on the menu instead of following the id rotation.
 */
public class CriticCustomer extends Customer {
    public CriticCustomer(int id) {
        super(id);
    }

    @Override
    public int waitingLoss() {
        return 12;
    }

    @Override
    public MenuItem chooseFood(Menu menu) {
        return menu.getMostExpensive();
    }

    @Override
    public String typeName() {
        return "Critic";
    }
}
