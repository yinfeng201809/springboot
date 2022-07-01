package com.example.demo.study.thread.resourceshare.garden;

import java.util.Random;

public class Count {
    private int count = 0;
    private Random rand = new Random(47);

    public synchronized int increment() {
        if (rand.nextBoolean()) {
            Thread.yield();
        }
        count++;
        return count;
    }
    public synchronized int value(){
        return count;
    }
}
