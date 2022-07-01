package com.example.demo.study.thread.resourceshare;

public class EvenGenerator extends IntGenerator{
    private int currentEvenValue = 0;


    @Override
    public synchronized int next() {
        ++currentEvenValue;
        Thread.yield();
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
}
