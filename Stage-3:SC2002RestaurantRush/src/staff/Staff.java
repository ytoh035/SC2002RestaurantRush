package staff;

public abstract class Staff {
    private String name;
    private boolean actedThisTurn;

    public Staff(String name) {
        this.name = name;
        this.actedThisTurn = false;
    }

    public void resetTurn() {
        this.actedThisTurn = false;
    }

    public boolean hasActed() {
        return actedThisTurn;
    }

    protected void markActed() {
        this.actedThisTurn = true;
    }
}