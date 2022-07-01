package com.example.demo.study.FutureTest;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.Executors;

public class ListenFutureTest{


    public static void main(String[] args) {

        testListenFuture();

    }


    public static void testListenFuture() {

        System.out.println("主任务执行完，开始异步执行副任务1.....");

        ListeningExecutorService pool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));

        ListenableFuture<String> future = pool.submit(new Task());
        FutureCallbackImpl callback = new FutureCallbackImpl();
        Futures.addCallback(future,callback,pool);

        System.out.println("副本任务启动,回归主任务线，主业务正常返回2.....");

    }

}