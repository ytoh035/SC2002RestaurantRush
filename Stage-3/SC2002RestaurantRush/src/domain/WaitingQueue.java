package domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class WaitingQueue {
    private final Deque<Customer> waiting = new ArrayDeque<>();

    public void enqueue(Customer c) { waiting.addLast(c); }
    public void remove(Customer c) { waiting.remove(c); }
    public boolean contains(Customer c) { return waiting.contains(c); }
    public boolean isEmpty() { return waiting.isEmpty(); }
    public int size() { return waiting.size(); }

    /** Copy, oldest first. */
    public List<Customer> getAll() { return new ArrayList<>(waiting); }
}
