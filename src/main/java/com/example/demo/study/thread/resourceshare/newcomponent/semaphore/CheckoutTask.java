package com.example.demo.study.thread.resourceshare.newcomponent.semaphore;

import java.util.concurrent.TimeUnit;

public class CheckoutTask<T> implements Runnable {

    private static int counter=0;
    private final int id = counter++;
    private Pool<T> pool;

    public CheckoutTask(Pool<T> pool) {
        this.pool=pool;
    }

    @Override
    public void run() {
        try {
            T item=pool.checkOut();
            System.out.println(this + " checkout: " + item);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(this + " checkin: " + item);
            pool.checkIn(item);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        return "TaskoutCheck "+id;
    }
}
