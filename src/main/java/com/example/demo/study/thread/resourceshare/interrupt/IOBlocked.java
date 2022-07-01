package com.example.demo.study.thread.resourceshare.interrupt;

import com.example.demo.study.enumstudy.Input;

import java.io.IOException;
import java.io.InputStream;

public class IOBlocked implements Runnable {

    private InputStream in;

    public IOBlocked(InputStream is) {
        this.in=is;
    }
    @Override
    public void run() {
        try {
            System.out.println("waiting for read");
            in.read();
        } catch (IOException e) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("interrupted from io blocked");
            } else {
                throw new RuntimeException(e);
            }
        }
        System.out.println("existing io blocked .run()");
    }
}
