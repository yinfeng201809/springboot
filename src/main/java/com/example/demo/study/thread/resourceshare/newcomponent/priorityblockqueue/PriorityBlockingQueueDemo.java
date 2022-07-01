package com.example.demo.study.thread.resourceshare.newcomponent.priorityblockqueue;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue<>();
        priorityBlockingQueue.offer(2);
        priorityBlockingQueue.offer(1);
        priorityBlockingQueue.offer(3);
        for (Integer i : priorityBlockingQueue) {
            System.out.println(i);
        }
        System.out.println(priorityBlockingQueue.take());;
    }
}
