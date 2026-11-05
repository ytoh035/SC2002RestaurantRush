package domain;

import core.GameStats;
import enums.CustomerState;
import enums.OrderStatus;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Restaurant {
    private final List<Table> tables = new ArrayList<>();
    private final WaitingQueue queue = new WaitingQueue();
    private final Menu menu;
    private final List<Customer> customers = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();
    private final GameStats stats = new GameStats();

    public Restaurant(int tableCount, Menu menu) {
        this.menu = menu;
        for (int i = 1; i <= tableCount; i++) {
            tables.add(new Table(i));
        }
    }

    public void addToQueue(Customer c) {
        customers.add(c);
        queue.enqueue(c);
    }

    public Optional<Table> findFreeTable() {
        return tables.stream().filter(Table::isFree).findFirst();
    }

    public Optional<Table> getTableOf(Customer c) {
        return tables.stream().filter(t -> t.occupantEquals(c)).findFirst();
    }

    public Order createOrder(Customer c, MenuItem item) {
        Order o = new Order(orders.size() + 1, c, item);
        orders.add(o);
        return o;
    }

    public Optional<Order> getActiveOrderOf(Customer c) {
        return orders.stream().filter(o -> o.getCustomer() == c && o.isActive()).findFirst();
    }

    public List<Order> getOrdersWithStatus(OrderStatus status) {
        List<Order> out = new ArrayList<>();
        for (Order o : orders) if (o.getStatus() == status) out.add(o);
        return out;
    }

    public List<Customer> getCustomersInState(CustomerState state) {
        List<Customer> out = new ArrayList<>();
        for (Customer c : customers) if (c.getState() == state) out.add(c);
        return out;
    }

    public List<Customer> getReadyToPayOldestFirst() {
        List<Customer> ready = getCustomersInState(CustomerState.READY_TO_PAY);
        ready.sort(Comparator.comparingInt(Customer::getReadyToPayTurn).thenComparingInt(Customer::getId));
        return ready;
    }

    public void removeAbandoned(Customer c) {
        queue.remove(c);
        getTableOf(c).ifPresent(Table::free);
        getActiveOrderOf(c).filter(Order::isUnfinished).ifPresent(Order::cancel);
        c.setState(CustomerState.ABANDONED);
        stats.recordAbandon();
    }

    public Optional<Customer> getCustomerById(int id) {
        return customers.stream().filter(c -> c.getId() == id).findFirst();
    }

    public Optional<Table> getTableById(int id) {
        return tables.stream().filter(t -> t.getTableId() == id).findFirst();
    }

    public Optional<Order> getOrderById(int id) {
        return orders.stream().filter(o -> o.getOrderId() == id).findFirst();
    }

    public GameStats getStats() { return stats; }
    public WaitingQueue getQueue() { return queue; }
    public Menu getMenu() { return menu; }
    public List<Order> getOrders() { return orders; }
    public List<Table> getTables() { return tables; }
    public List<Customer> getCustomers() { return customers; }
}
