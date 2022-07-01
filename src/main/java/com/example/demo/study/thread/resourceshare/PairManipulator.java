package com.example.demo.study.thread.resourceshare;

public class PairManipulator implements Runnable{
    private PairManager pm;

    public PairManipulator(PairManager pairManager) {
        pm=pairManager;
    }
    @Override
    public void run() {
        while (true) {
            pm.increment();
        }
    }

    public String toString() {
        return "Pair: " + pm.getPair() + " checkCounter:" + pm.checkCounter.get();
    }
}
