package com.example.demo.study.thread.resourceshare.newcomponent.delayqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class DelayedTask implements Runnable, Delayed {
    private static int counter=0;
    private final int id = counter++;
    private final int delta;
    private final long trigger;

    protected static List<DelayedTask> sequence = new ArrayList<>();

    public DelayedTask(int delayInMilliseconds) {
        delta=delayInMilliseconds;
        trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
        sequence.add(this);
    }

    @Override
    public int compareTo(Delayed o) {
        DelayedTask that = (DelayedTask) o;
        if (trigger < that.trigger) {
            return -1;
        } else if (trigger > that.trigger) {
            return 1;
        }
        return 0;
    }

    @Override
    public void run() {
        System.out.println(this + " ");
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger-System.nanoTime(),TimeUnit.NANOSECONDS);
    }

    public String toString() {
        return String.format("%1$-4d",delta)+" task "+id;
    }

    public String summary() {
        return "(" + id + ":" + delta + ")";
    }

    public static class EndSentinel extends DelayedTask {
        private ExecutorService exec;

        public EndSentinel(int delay, ExecutorService e) {
            super(delay);
            exec = e;
        }
        @Override
        public void run() {
            for (DelayedTask pt : sequence) {
                System.out.println(pt.summary() + " ");
            }
            System.out.println();
            System.out.println(this + " shutdownnow");
            exec.shutdownNow();
        }
    }

}
