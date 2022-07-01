package com.example.demo.study.thread.resourceshare;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable{
    private IntGenerator generator;
    private final int id;

    public EvenChecker(IntGenerator g, int ident) {
        generator =g;
        id=ident;
    }
    @Override
    public void run() {
        while (!generator.isCanceled()) {
            int val=generator.next();
            if (val % 2 != 0) {
                System.out.println(val + " is not even");
                generator.cancel();
            }
        }
    }

    public static void test(IntGenerator generator, int count) {
        ExecutorService exec= Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            exec.execute(new EvenChecker(generator, i));
        }
        exec.shutdown();
    }

    public static void test(IntGenerator gp) {
        test(gp,10);
    }
}
