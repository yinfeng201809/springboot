package com.example.demo.study.thread.exception;

public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(t.getName()+ " caught:" + e);
    }
}
