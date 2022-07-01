package com.example.demo.study.thread.resourceshare.newcomponent.simulation.bank;

import java.util.concurrent.TimeUnit;

public class Teller implements Runnable,Comparable<Teller>{

    private static int counter=0;
    private final int id = counter++;

    private int customerServed = 0;
    private CustomerLine customers;
    private boolean servingCustomerLine = true;

    public Teller(CustomerLine cq) {
        customers=cq;
    }
    @Override
    public int compareTo(Teller o) {
        return customerServed < o.customerServed ? -1 : (customerServed == o.customerServed ? 0 : 1);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Customer customer=customers.take();
                TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
                synchronized (this) {
                    customerServed++;
                    while (!servingCustomerLine) {
                        wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("teller interrupted");
            throw new RuntimeException(e);
        }
        System.out.println("teller teminated");
    }

    public synchronized void doOtherThingsElse() {
        customerServed =0;
        servingCustomerLine=false;
    }

    public synchronized void serveCustomLine() {
        assert !servingCustomerLine : "aleady serving" + this;
        servingCustomerLine=true;
        notifyAll();
    }

    public String toString() {
        return "teller " + id + " ";
    }

    public String shortString() {
        return "出纳员id："+id;
    }


}
