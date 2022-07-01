package com.example.demo.study.thread.resourceshare.newcomponent.simulation.bank;

import java.util.concurrent.ArrayBlockingQueue;

public class CustomerLine extends ArrayBlockingQueue<Customer> {
    public CustomerLine(int capacity) {
        super(capacity);
    }

    public String toString() {
        if (this.size() == 0) {
            return "[empty]";
        }
        StringBuilder sb = new StringBuilder();
        for (Customer customer : this) {
            sb.append(customer);
        }
        return sb.toString();
    }
}
