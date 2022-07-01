package com.example.demo.study.thread.resourceshare.garden;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Entrance implements Runnable {

    private static Count count = new Count();

    private static List<Entrance> entranceList = new ArrayList<>();
    private int number=0;

    private final  int id;
    private static volatile boolean canceled=false;

    public static void cancel() {
        canceled=true;
    }

    public Entrance(int id) {
        this.id = id;
        entranceList.add(this);
    }

    @Override
    public void run() {
        while (!canceled) {
            synchronized (this) {
                ++number;
            }
            System.out.println(this + " total:" + count.increment());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("stopping "+this);
    }

    public synchronized int getValue() {
        return number;
    }

    public String toString() {
        return "Entrance " + id + ":" + getValue();
    }

    public static int getTotalCount() {
        return count.value();
    }

    public static int sumEntrances() {
        int sum=0;
        for (Entrance entrance : entranceList) {
            sum += entrance.getValue();
        }
        return sum;
    }







}
