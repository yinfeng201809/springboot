package com.example.demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {

    private static final int THREAD_COUNT = 100;
    private static ExecutorService threadPool= Executors.newFixedThreadPool(THREAD_COUNT);
    private static Semaphore s = new Semaphore(10);
    public static void main(String[] args) {
        for (int i = 0; i< THREAD_COUNT; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "可用许可数： "+s.availablePermits());
                    System.out.println(Thread.currentThread().getName() + "等待获取许可证的线程数： "+s.getQueueLength());
                    System.out.println(Thread.currentThread().getName() + "是否有线程正在等待获取许可： "+s.hasQueuedThreads());
                    s.acquire();
                    System.out.println(Thread.currentThread().getName() + " save data" + finalI);
                    s.release();
                    TimeUnit.SECONDS.sleep(1000);
                } catch (InterruptedException e) {
                }
            });
        }
        threadPool.shutdown();
    }

}
