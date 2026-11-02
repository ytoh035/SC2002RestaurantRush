package domain;

public class Table {
    private final int tableId;
    private Customer occupant;

    public Table(int tableId) {
        this.tableId = tableId;
    }

    public boolean isFree() { return occupant == null; }
    public boolean occupantEquals(Customer c) { return occupant == c; }
    public void seat(Customer c) { this.occupant = c; }
    public void free() { this.occupant = null; }
    public int getTableId() { return tableId; }
    public Customer getOccupant() { return occupant; }
}
