package com.example.demo.study.thread.resourceshare.notify;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NotifyVsNotifyAll {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new Task());
        }
        exec.execute(new Task2());
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            boolean prod=true;
            @Override
            public void run() {
                if (prod) {
                    System.out.println("唤醒一个 ");
                    Task.blocker.notifyOneThread();
                    prod=false;
                }else {
                    System.out.println("唤醒所有");
                    Task.blocker.notifyAllThread();
                    prod=true;
                }
            }
        },400,400);

        TimeUnit.SECONDS.sleep(5);
        timer.cancel();
        System.out.println("timer canceled");
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("Task2 notifyAll");
        Task2.blocker.notifyAllThread();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("shutting down");
        exec.shutdownNow();
    }
}
