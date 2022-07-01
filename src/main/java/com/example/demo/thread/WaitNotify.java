package com.example.demo.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();
    public static void main(String[] args) throws Exception {
        Thread waitThread = new Thread(new Wait(), "等待线程");
        waitThread.start();
        lock.wait();
        synchronized (lock) {
            System.out.println(Thread.currentThread() + "我获取锁了" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.notify();
            System.out.println(Thread.currentThread() + "我获取锁并notify 了" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        }
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "提醒线程");
        notifyThread.start();
    }
    static class Wait implements Runnable {
        public void run() {
            // 加锁，拥有lock的Monitor
            synchronized (lock) {
                System.out.println(Thread.currentThread() + "我获取锁了，你们都先等会" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 当条件不满足时，继续wait，同时释放了lock的锁
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true. wait @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        System.out.println(Thread.currentThread() + "我释放锁了，你们玩吧" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                        System.out.println(Thread.currentThread() + "我获取锁了，我要干活了" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    } catch (InterruptedException e) {
                    }
                }
                // 条件满足时，完成工作
                System.out.println(Thread.currentThread() + " flag is false. running @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }
    static class Notify implements Runnable {
        public void run() {
            // 加锁，拥有lock的Monitor
            synchronized (lock) {
                // 获取lock的锁，然后进行通知，通知时不会释放lock的锁，
                // 直到当前线程释放了lock后，WaitThread才能从wait方法中返回
                System.out.println(Thread.currentThread() + " 获取锁了，我要notify了. notify @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "释放锁了，你们可以获取锁了" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
            // 再次加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " 嘿嘿 ，我又获取锁了，先 sleep @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + " 嘿嘿 ，我休息完了，锁，你们玩吧 @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }
}
