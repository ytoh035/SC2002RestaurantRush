package domain;

import enums.CustomerState;

public abstract class Customer {
    private final int id;
    private int satisfaction;
    private CustomerState state;
    private int seatedTurn;
    private int servedTurn;
    private int readyToPayTurn;

    public Customer(int id) {
        this.id = id;
        this.satisfaction = 100;
        this.state = CustomerState.QUEUED;
    }
    
    public abstract int waitingLoss();

    public abstract String typeName();

    public MenuItem chooseFood(Menu menu) {
        return menu.get((id - 1) % menu.size());
    }

    public int applyDiscount(int priceCents) {
        return priceCents;
    }

    public boolean isWaitingForService() {
        return state == CustomerState.QUEUED
            || state == CustomerState.SEATED
            || state == CustomerState.ORDERED;
    }

    public void applyWaitingLoss() {
        if (isWaitingForService()) {
            satisfaction = Math.max(0, satisfaction - waitingLoss());
        }
    }

    public boolean isAbandoned() {
        return isWaitingForService() && satisfaction == 0;
    }

    public int getId() { return id; }
    public int getSatisfaction() { return satisfaction; }
    public CustomerState getState() { return state; }
    public void setState(CustomerState state) { this.state = state; }
    public int getSeatedTurn() { return seatedTurn; }
    public void setSeatedTurn(int turn) { this.seatedTurn = turn; }
    public int getServedTurn() { return servedTurn; }
    public void setServedTurn(int turn) { this.servedTurn = turn; }
    public int getReadyToPayTurn() { return readyToPayTurn; }
    public void setReadyToPayTurn(int turn) { this.readyToPayTurn = turn; }
}
