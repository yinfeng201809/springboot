package com.example.demo.study.thread.resourceshare.newcomponent.countlatch;

import java.util.concurrent.CountDownLatch;

public class WaitingTask implements Runnable{
    private static int counter=0;

    private final int id=counter++;

    private final CountDownLatch latch;

    WaitingTask(CountDownLatch latch) {
        this.latch=latch;
    }
    @Override
    public void run() {
        try {
            latch.await();
            System.out.println("latch barrier passed for"+ this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        return String.format("waiting task %1$-3d ", id);
    }
}
