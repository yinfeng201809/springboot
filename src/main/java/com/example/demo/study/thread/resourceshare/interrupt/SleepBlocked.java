package com.example.demo.study.thread.resourceshare.interrupt;

import java.util.concurrent.TimeUnit;

public class SleepBlocked implements Runnable{
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("sleepThread interrupted");
            throw new RuntimeException(e);
        }
        System.out.println("existing sleepBlocked.run()");
    }
}
