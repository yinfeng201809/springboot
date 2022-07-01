package com.example.demo.study.thread.resourceshare.garden;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OrnamentalGarden {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new Entrance(i));
        }
        TimeUnit.SECONDS.sleep(3);
        Entrance.cancel();
        exec.shutdown();
        if (!exec.awaitTermination(250, TimeUnit.MILLISECONDS)) {
            System.out.println("some tasks were not terminated");
        }
        System.out.println("total:" + Entrance.getTotalCount());
        System.out.println("sum of entrances:" + Entrance.sumEntrances());
    }
}
