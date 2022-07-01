package com.example.demo.study.thread.resourceshare.consumerproduct;

public class Meal {
    private final int orderNum;

    public Meal(int orderNum) {
        this.orderNum=orderNum;
    }

    public String toString() {
        return "meal" + orderNum;
    }
}
