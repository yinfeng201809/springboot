package com.example.demo.study.thread.resourceshare.consumerproduct;

import org.checkerframework.checker.units.qual.C;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Restaurant {

    Meal meal;
    ExecutorService exec= Executors.newCachedThreadPool();
    Waiter waiter = new Waiter(this);
    Chef chef = new Chef(this);

    public Restaurant() {
        exec.execute(waiter);
        exec.execute(chef);
    }

    public static void main(String[] args) {
        new Restaurant();
    }
}
