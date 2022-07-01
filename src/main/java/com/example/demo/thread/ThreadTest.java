package com.example.demo.thread;

import com.example.demo.domain.User;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

public class ThreadTest {

    private static User user = null;

    public synchronized void t() throws InterruptedException {
        System.out.println("我等1秒再干");
        wait(1000);
    }


    public static void main(String[] args) throws InterruptedException {
        ThreadTest threadTest = new ThreadTest();

//        new Thread(()->{
//            try {
//                TimeUnit.SECONDS.sleep(4);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            user = new User();
//            System.out.println("user bu为空");
//        }).start();
        threadTest.testWaitTime(10000);
        user = new User();
        threadTest.notify();


//        test1();
    }

    public  synchronized User testWaitTime(long millSeconds) throws InterruptedException {
        long future = System.currentTimeMillis() + millSeconds;
        long remaining = millSeconds;
        while (user == null && remaining > 0) {
            wait(remaining);
            remaining = future - System.currentTimeMillis();
        }
        System.out.println("等不及了，我要走了");
        return user;
    }

    private static void test1() {
        User user = new User();
        Thread thread=new Thread(()->{
            synchronized (user) {
                while (StringUtils.isEmpty(user.getEmail())) {
                    try {
                        user.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "干完活");
            }
        });
        thread.start();
        try {
            thread.join();
            thread.isAlive();
            System.out.println("deng 4 miao");
            Thread.sleep(4000);
            System.out.println("等完");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (user) {
            user.setEmail("a");
            user.notify();
        }
    }
}
