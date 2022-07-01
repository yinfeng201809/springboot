package com.example.demo.study.thread.resourceshare.interrupt;

public class SynchronizedBlocked implements Runnable{

    public synchronized void f() {
        while (true) {
            Thread.yield();
        }
    }

    public SynchronizedBlocked() {
        new Thread(){
            public void run() {
                f();
            }
        }.start();
    }
    @Override
    public void run() {
        System.out.println("try to call f()");
        f();
        System.out.println("exit synchronizedBlocked.run()");
    }
}
