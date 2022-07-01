package com.example.demo.study.thread.resourceshare.newcomponent.countlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TaskPortion implements Runnable{
    private static int counter=0;

    private final int id = counter++;
    private static Random random = new Random(47);
    private final CountDownLatch latch;

    TaskPortion(CountDownLatch countDownLatch) {
        latch=countDownLatch;
    }
    public void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(random.nextInt(2000));
        System.out.println(this+" completed");
    }

    public String toString() {
        return String.format("%1$-3d ", id);
    }
    @Override
    public void run() {
        try {
            doWork();
            latch.countDown();
        } catch (InterruptedException e) {
            System.out.println(this+" interrupted");
        }
    }
}
