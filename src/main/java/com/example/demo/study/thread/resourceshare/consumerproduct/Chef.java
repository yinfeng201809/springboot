package com.example.demo.study.thread.resourceshare.consumerproduct;

import java.util.concurrent.TimeUnit;

public class Chef implements Runnable{
    private Restaurant restaurant;

    private int count=0;

    public Chef(Restaurant restaurant) {
        this.restaurant=restaurant;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal != null) {
                        wait();
                    }
                    if (++count == 10) {
                        System.out.println("没食物了，关闭");
                        restaurant.exec.shutdownNow();
                    }
                    System.out.println("order up");
                    synchronized (restaurant.waiter) {
                        restaurant.meal = new Meal(count);
                        restaurant.waiter.notifyAll();

                    }
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("chef interrupted");
        }
    }
}
