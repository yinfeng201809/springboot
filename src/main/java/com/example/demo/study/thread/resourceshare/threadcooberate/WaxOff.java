package com.example.demo.study.thread.resourceshare.threadcooberate;

import java.util.concurrent.TimeUnit;

public class WaxOff implements Runnable{
    private Car car;

    public WaxOff(Car car) {
        this.car=car;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForWaxing();
                System.out.println("抛光开始 off off off off off off");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
                System.out.println("抛光结束");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束抛光 任务");
    }
}
