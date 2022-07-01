package com.example.demo.study.thread.resourceshare.newcomponent.delayqueue;

import java.util.concurrent.DelayQueue;

public class DelayedTaskConsumer implements Runnable {

    private DelayQueue<DelayedTask> q;

    public DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
        this.q = q;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                q.take().run();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("finished consumer");

    }
}
