package com.example.demo.thread.threadpool;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPollTest {

    static AtomicInteger index = new AtomicInteger();
    public static void main(String[] args) {
        ThreadPool<Job> threadPool = new DefaultThreadPool(10);

        Job job = new Job();
        int num=0;
        while (num < 1000) {
            threadPool.execute(job);
            num++;
        }

    }

    public static class Job implements Runnable {


        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " 这是一个工作" + index.incrementAndGet());
        }
    }

}
