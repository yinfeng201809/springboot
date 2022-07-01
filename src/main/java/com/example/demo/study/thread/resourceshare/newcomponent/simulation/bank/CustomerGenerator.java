package com.example.demo.study.thread.resourceshare.newcomponent.simulation.bank;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CustomerGenerator implements Runnable {
    private CustomerLine customers;
    private static Random rand = new Random(47);

    public CustomerGenerator(CustomerLine cq) {
        customers = cq;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(300));
                customers.add(new Customer(rand.nextInt(1000)));
            }
        } catch (InterruptedException e) {
            System.out.println("customer generator interrupted");
            throw new RuntimeException(e);
        }
        System.out.println("customer terminated");
    }
}
