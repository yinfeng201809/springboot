package com.example.demo.study.FutureTest;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Task implements Callable<String> {


    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(1);
// int a =1/0;
        return "task done";
    }

}
