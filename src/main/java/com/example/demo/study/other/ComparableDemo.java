package com.example.demo.study.other;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ComparableDemo {
    public static void main(String[] args) {
        Order order = new Order(1, "a");
        Order order1 = new Order(3, "c");
        Order order2 = new Order(222, "b");
        List<Order> orders = Arrays.asList(order, order1, order2);
        orders = orders.stream().sorted().collect(Collectors.toList());
        for (Order o : orders) {
            System.out.println(o);
        }
    }

    static class Order implements Comparable<Order>{
        private int num;
        private String name;

        public Order(int num, String name) {
            this.num=num;
            this.name = name;
        }

        @Override
        public int compareTo(Order o) {
            if (num < o.num) {
                return 1;
            } else if (num > o.num) {
                return -1;
            }
            return 0;
        }

        public String toString() {
            return "id:"+num+" name:"+name;
        }
    }
}
