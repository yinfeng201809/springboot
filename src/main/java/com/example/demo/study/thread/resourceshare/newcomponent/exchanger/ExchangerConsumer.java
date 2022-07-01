package com.example.demo.study.thread.resourceshare.newcomponent.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerConsumer implements Runnable{

    private Exchanger<String> exchanger;

    public ExchangerConsumer(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        String consumer = "consumer";
        String msg = null;
        try {
            msg = exchanger.exchange(consumer);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("consumer 收到了消息 "+msg);
    }
}
