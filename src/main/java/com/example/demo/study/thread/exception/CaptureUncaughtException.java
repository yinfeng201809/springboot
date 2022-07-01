package com.example.demo.study.thread.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CaptureUncaughtException {
    public static void main(String[] args) {
        ExecutorService executor= Executors.newCachedThreadPool(new HandlerThreadFactory());
        executor.execute(new ExceptionThread2());

    }
}
