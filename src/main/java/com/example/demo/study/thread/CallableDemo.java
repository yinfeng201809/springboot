package com.example.demo.study.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService exec= Executors.newCachedThreadPool();
        List<Future<String>> resultList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            resultList.add(exec.submit(new TaskWithResult(i)));
        }
        resultList.stream().forEach(item->{
            try {
                if (item.isDone()) {
                    System.out.println("done");
                    System.out.println(item.get());
                }else {
                    System.out.println("undone");
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
