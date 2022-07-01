package com.example.demo.study.thread.resourceshare;

public class Accessor implements Runnable{
    private final int id;

    public Accessor(int idn) {
        id=idn;
    }
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
//            ThreadLocalTest
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    public String toString() {
        return "#" + id + ":" + ThreadLocalVariableHolder.get();
    }
}
