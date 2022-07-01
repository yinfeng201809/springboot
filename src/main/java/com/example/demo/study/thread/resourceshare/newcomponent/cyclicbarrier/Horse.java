package com.example.demo.study.thread.resourceshare.newcomponent.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Horse implements Runnable{
    private static int counter=0;

    private final int id=counter++;

    private int strides=0;

    private static Random random = new Random(47);
    private static CyclicBarrier barrier;

    public Horse(CyclicBarrier b) {
        barrier=b;
    }

    public synchronized int getStrides() {
        return strides;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    int newStrides=random.nextInt(3);
                    strides += newStrides;
                    System.out.println("horse" + id + " 走了 " + strides);
                }
                barrier.await();
                System.out.println("horse"+ id +" 可以开始跑了 ");
            }
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        return "horse " + id + " ";
    }

    public String tracks() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < getStrides(); i++) {
            sb.append("*");
        }
        sb.append(id);
        return sb.toString();
    }
}
