package com.example.demo.study.thread.resourceshare.threadcooberate;

import java.util.concurrent.TimeUnit;

public class WaxOn implements Runnable{
    private Car car;

    public WaxOn(Car car) {
        this.car=car;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("开始打蜡 on on on on on on ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                System.out.println("打蜡结束，等待抛光");
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束打蜡 任务");
    }
}
