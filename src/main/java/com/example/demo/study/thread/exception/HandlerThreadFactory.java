package com.example.demo.study.thread.exception;

import java.util.concurrent.ThreadFactory;

public class HandlerThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        System.out.println(this + "creating new thread");
        Thread thread = new Thread(r);
        System.out.println("created " + thread);
        thread.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println("exceptionhander=" + thread.getUncaughtExceptionHandler());
        return thread;
    }
}
