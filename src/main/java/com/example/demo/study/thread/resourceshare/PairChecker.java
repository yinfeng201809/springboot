package com.example.demo.study.thread.resourceshare;

public class PairChecker implements Runnable{
    private PairManager pm;

    public PairChecker(PairManager pm) {
        this.pm=pm;
    }

    @Override
    public void run() {
        while (true) {
            pm.checkCounter.incrementAndGet();
            pm.getPair().checkState();
        }
    }
}
