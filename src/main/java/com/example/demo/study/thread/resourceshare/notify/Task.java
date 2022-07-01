package com.example.demo.study.thread.resourceshare.notify;

public class Task implements Runnable{
    static Blocker blocker=new Blocker();

    @Override
    public void run() {
        blocker.waitingForCall();
    }
}
