package com.example.demo.study.thread.resourceshare.newcomponent.simulation.bank;

public class Customer {
    private final int serviceTime;
    private static int counter=0;
    private final int id = counter++;

    public Customer(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    @Override
    public String toString() {
        return "顾客" + id + "需要的服务时间： [" + serviceTime + "] ";
    }
}
