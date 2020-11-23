package com.johar.jeektime.springjava8.demo;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName: StreamDemo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/21 09:13
 * @Since: 1.0.0
 */
public class StreamDemo {

    public static void main(String[] args) {
        flatMapTest();

        peekTest();

        print("");

        print("    ");

        print("abc");
    }

    private static void print(String str){
        Optional.ofNullable(str).filter(StringUtils::hasText).ifPresent(System.out::println);
    }

    private static void peekTest() {
        List<String> stringList = Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
        stringList.stream().forEach(System.out::println);
    }

    private static void flatMapTest() {
        List<Integer> array1 = Arrays.asList(1,2,3);
        List<Integer> array2 = Arrays.asList(3,4,5,6,7,8);
        List<Integer> array3 = Arrays.asList(9,10);

        List<List<Integer>> lists = new ArrayList<>();
        lists.add(array3);
        lists.add(array1);
        lists.add(array2);

        List<Integer> result = lists.stream().flatMap(child -> child.stream()).collect(Collectors.toList());
        result.stream().forEach(System.out::println);
    }
}