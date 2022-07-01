package com.example.demo.study.thread.resourceshare.newcomponent.countlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {
    static final int size=100;

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(size);
        for (int i = 0; i < 10; i++) {
            exec.execute(new WaitingTask(latch));
        }
        for (int i = 0; i < size; i++) {
            exec.execute(new TaskPortion(latch));
        }
        System.out.println("启动了所有任务");
        exec.shutdown();
    }
}
