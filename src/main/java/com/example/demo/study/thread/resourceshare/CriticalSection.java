package com.example.demo.study.thread.resourceshare;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CriticalSection {
    static void testApproaches(PairManager pairManager1, PairManager pairManager2) {
        ExecutorService exec = Executors.newCachedThreadPool();
        PairManipulator pm1 = new PairManipulator(pairManager1);
        PairManipulator pm2 = new PairManipulator(pairManager2);

        PairChecker pairChecker1 = new PairChecker(pairManager1);
        PairChecker pairChecker2 = new PairChecker(pairManager2);
        exec.execute(pm1);
        exec.execute(pm2);
        exec.execute(pairChecker1);
        exec.execute(pairChecker2);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            System.out.println("sleep interrupted");
            throw new RuntimeException(e);
        }
        System.out.println("pm1:" + pm1 + " \npm2: "  + pm2);
        System.exit(0);


    }

    public static void main(String[] args) {
        PairManager pairManager1 = new PairManage1();
        PairManager pairManager2 = new PairManager2();
        testApproaches(pairManager1, pairManager2);
    }
}
