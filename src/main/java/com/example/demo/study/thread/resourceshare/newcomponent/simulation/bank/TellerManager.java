package com.example.demo.study.thread.resourceshare.newcomponent.simulation.bank;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class TellerManager implements Runnable{
    private ExecutorService exec;
    private CustomerLine customers;
    private PriorityQueue<Teller> workingTellers = new PriorityQueue<>();
    private Queue<Teller> tellersDongOtherThings = new LinkedList<>();
    private int adjustPeriod;
    private static Random rand = new Random(47);

    public TellerManager(ExecutorService e, CustomerLine customers, int adjustPeriod) {
        exec=e;
        this.customers=customers;
        this.adjustPeriod=adjustPeriod;
        Teller teller = new Teller(customers);
        exec.execute(teller);
        workingTellers.add(teller);
    }

    public void adjustTellerNumber() {
        if (customers.size() / workingTellers.size() > 2) {
            System.out.println("出纳人太少了，需要调个人过来");
            if (tellersDongOtherThings.size() > 0) {
                Teller teller=tellersDongOtherThings.remove();
                teller.serveCustomLine();
                workingTellers.offer(teller);
                System.out.println("从其它地方调了一个出纳");
                return;
            }
            Teller teller = new Teller(customers);
            exec.execute(teller);
            workingTellers.add(teller);
            System.out.println("新招聘了一个出纳");
            return;
        }
        if (workingTellers.size() > 1 && customers.size() / workingTellers.size() < 2) {
            System.out.println("出纳太多了，调个人去其它地方");
            reassignOneTeller();
        }
        if (customers.size() == 0) {
            while (workingTellers.size() > 1) {
                reassignOneTeller();
            }
        }
    }

    private void reassignOneTeller() {
        Teller teller=workingTellers.poll();
        teller.doOtherThingsElse();
        tellersDongOtherThings.offer(teller);
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(adjustPeriod);
                adjustTellerNumber();
                System.out.println(customers + " {");
                for (Teller teller : workingTellers) {
                    System.out.println(teller.shortString() + " ");
                }
                System.out.println("}");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(this+" terminated");
    }

    public String toString() {
        return "TellManager ";
    }
}
