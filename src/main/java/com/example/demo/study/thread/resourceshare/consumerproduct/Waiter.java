package com.example.demo.study.thread.resourceshare.consumerproduct;

public class Waiter implements Runnable{
    private Restaurant restaurant;

    public Waiter(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal == null) {
                        wait();
                    }
                }
                System.out.println("get meal "+restaurant.meal);
                synchronized (restaurant.chef) {
                    restaurant.meal=null;
                    restaurant.chef.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("waiter interrupted");
        }
    }
}
