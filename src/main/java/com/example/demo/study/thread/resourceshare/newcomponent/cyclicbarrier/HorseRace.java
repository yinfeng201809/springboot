package com.example.demo.study.thread.resourceshare.newcomponent.cyclicbarrier;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HorseRace {
    static final int FINISH_LINE = 75;

    private List<Horse> horses = new ArrayList<>();
    private ExecutorService exec = Executors.newCachedThreadPool();

    private CyclicBarrier cyclicBarrier;

    public HorseRace(int nHorses, final int pause) {
        cyclicBarrier = new CyclicBarrier(nHorses, () -> {
            StringBuffer s = new StringBuffer();
            System.out.println("打印任务的线程是 "+Thread.currentThread().getName());
            for (int i = 0; i < FINISH_LINE; i++) {
                s.append("=");
            }
            System.out.println(s);
            for (Horse horse : horses) {
                System.out.println(horse.tracks());
            }
            for (Horse horse : horses) {
                if (horse.getStrides() >= FINISH_LINE) {
                    System.out.println(horse + " won!");
                    exec.shutdownNow();
                    return;
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(pause);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        for (int i = 0; i < nHorses; i++) {
            Horse horse = new Horse(cyclicBarrier);
            horses.add(horse);
            exec.execute(horse);
        }
    }

    public static void main(String[] args) {
        int nHorse=7;
        int pause = 200;
        new HorseRace(nHorse, pause);
    }
}
