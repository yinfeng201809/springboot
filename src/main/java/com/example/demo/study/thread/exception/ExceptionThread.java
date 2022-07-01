package com.example.demo.study.thread.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExceptionThread implements Runnable{
    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        try {
            ExecutorService exector= Executors.newCachedThreadPool();
            exector.execute(new ExceptionThread());
        } catch (Exception e) {
            System.out.println("exception found ");
            throw new RuntimeException(e);
        }

    }
}
