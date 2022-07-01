package com.example.demo.study.thread.resourceshare.newcomponent.simulation.bank;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BankTellerSimulation {
    static final int MAX_SIZE=50;
    static final int ADJUST_PERIOD=1000;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        CustomerLine customers = new CustomerLine(MAX_SIZE);
        exec.execute(new CustomerGenerator(customers));
        exec.execute(new TellerManager(exec, customers, ADJUST_PERIOD));
        TimeUnit.SECONDS.sleep(25);
        exec.shutdownNow();
    }
}
