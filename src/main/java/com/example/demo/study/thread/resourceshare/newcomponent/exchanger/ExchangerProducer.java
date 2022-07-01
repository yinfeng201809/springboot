package com.example.demo.study.thread.resourceshare.newcomponent.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerProducer implements Runnable{

    private Exchanger<String> exchanger;
    public ExchangerProducer(Exchanger<String> exchanger) {
        this.exchanger=exchanger;
    }
    @Override
    public void run() {
        String s = "abc";
        try {
            String msg = exchanger.exchange(s);
            System.out.println("生产者收到了消息： "+msg);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
