package com.example.demo.study.thread.resourceshare;

import org.checkerframework.checker.units.qual.A;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AttemptLocking {
    private ReentrantLock lock = new ReentrantLock();

    public void untimed() {
        boolean captured = lock.tryLock();
        try {
            System.out.println("tryLock:" + captured);
        }finally {
            if (captured) {
                lock.unlock();
            }
        }
    }

    public void timed() {
        boolean  captured=false;
        try {
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println("timed "+captured);
        }finally {
            if (captured) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        final AttemptLocking al=new AttemptLocking();
        al.untimed();
        al.timed();
        Thread t=new Thread(() -> {
            al.lock.lock();
            System.out.println("acquired");
        });
        t.setDaemon(true);
        t.start();
        Thread.yield();
        Thread.yield();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        al.untimed();
        al.timed();
    }
}
