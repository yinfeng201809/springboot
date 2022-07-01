package com.example.demo.study.thread.resourceshare.newcomponent.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExchangerDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Exchanger<String> exchanger=new Exchanger<>();
        exec.execute(new ExchangerConsumer(exchanger));
        exec.execute(new ExchangerProducer(exchanger));
        TimeUnit.SECONDS.sleep(1);
        exec.shutdown();
    }
}
