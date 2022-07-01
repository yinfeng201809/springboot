package com.example.demo.study.thread.resourceshare.notify;

public class Blocker {
    synchronized void waitingForCall() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(Thread.currentThread().getName() + "开始wait");
                wait();
                System.out.println(Thread.currentThread().getName() + "结束wait");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "interrupted ");
        }
    }

    synchronized void notifyOneThread() {
        notify();
    }

    synchronized void notifyAllThread() {
        notifyAll();
    }
}
