package com.example.demo.study.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadStudy {

    public static void main(String[] args) {
        ExecutorService executorService= Executors.newCachedThreadPool();

        executorService=Executors.newSingleThreadExecutor();
        for (int i = 0; i < 8; i++) {
            executorService.execute(new LiftOff());
        }
        executorService.shutdown();
    }

}
