package com.example.demo.study.mapstudy;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class MapStudy {
    public static void main(String[] args) {

        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(3, "3");
        map.put(4, "4");
        map.put(2, "2");
        map.put(5, "5");
        map.entrySet().forEach(entry->{
            System.out.println(entry.getKey()+" "+entry.getValue());
        });
        map.hashCode();
        String s = "";
        s.hashCode();

        System.out.println("=======================");
        SortedMap<Integer, String> sub = map.subMap(3, true, 4, true);
        sub.entrySet().forEach(entry->{
            System.out.println(entry.getKey()+" "+entry.getValue());
        });
    }
}
