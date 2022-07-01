package com.example.demo.study.thread.resourceshare;

public class PairManage1 extends PairManager{
    @Override
    public synchronized void increment() {
        p.incrementX();
        p.incrementY();
        store(getPair());
    }
}
