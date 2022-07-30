package com.example.demo.study.functionprogram;

import com.example.demo.domain.User;
import org.springframework.cglib.core.CollectionUtils;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Practice {


    public static void main(String[] args) {
        Consumer<Integer> con1 = item -> {
            System.out.println(item);

        };
        con1.andThen(con1).accept(4);

        Function<Integer, Integer> fun = (res) -> res + 1;
        Function<Integer, Integer> fun2 = (res) -> res * 10;
        Integer i = fun.compose(fun2).andThen(fun2).apply(2);
        System.out.println(i);

        Stream.iterate(1, n -> n + 1).limit(100).forEach(System.out::println);

        List<User> users = new ArrayList<>();

        Map<Long, Long> idUser = users.stream().collect(Collectors.groupingBy(User::getId,Collectors.counting()));
        Comparator<User> comparable = (a, b) -> {
            return 1;
        };
        BinaryOperator<User> binaryOperator = (a, b) -> a;
        users.stream().collect(Collectors.groupingBy(User::getId, Collectors.summingInt(item->item.getEmail().length())));





        User user = new User();
        user.setEmail("abc@163.com");
        Optional<User> optional = Optional.ofNullable(user);

        optional.ifPresent(item -> System.out.println(item.getEmail()));
        optional.orElseGet(()-> new User());
        user.setPhoneList(Arrays.asList("as","b","c"));

        Map<Integer, List<String>> optionalIntegerListMap = Optional.ofNullable(user).map(item -> item.getPhoneList())
                .map(item -> item.stream().collect(Collectors.groupingBy(i2 -> i2.length()))).orElse(null);
       List<String> optionalIntegerListMap2 = Optional.ofNullable(user).map(item -> item.getPhoneList()).map(item -> item.stream().collect(Collectors.toList())).orElse(null);
        System.out.println(optionalIntegerListMap2);
        List<String> list = new ArrayList<>();
        Optional<List<String>> optionalList = Optional.ofNullable(list);
        List<String> s2 = Optional.ofNullable(list)
                .filter(item -> item.stream().allMatch(item2 -> item2.length() > 0))
                .map(item2 -> item2.stream().map(item -> item.substring(1)).collect(Collectors.toList()))
                .orElse(null);
        System.out.println(s2);

        Map<Long, List<String>> idUser2 = users.stream().collect(Collectors.groupingBy(User::getId, Collectors.mapping(User::getNickName, Collectors.toList())));


    }
}


















