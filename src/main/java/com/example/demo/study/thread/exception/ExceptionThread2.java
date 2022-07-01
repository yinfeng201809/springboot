package com.example.demo.study.thread.exception;

public class ExceptionThread2 implements Runnable{
    @Override
    public void run() {
        Thread t= Thread.currentThread();
        System.out.println("run by " + t);
        System.out.println("exceptonHandler: " + t.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}
