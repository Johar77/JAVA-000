package com.johar.jeektime.springjava8.demo;

import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: GuavaDemo
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/21 09:33
 * @Since: 1.0.0
 */
public class GuavaDemo {

    public static void main(String[] args) {
        orderingTest();
    }

    private static void orderingTest() {
        Ordering<String> byLengthOrdering = new Ordering<String>() {
            @Override
            public int compare(@Nullable String s, @Nullable String t1) {
                return Ints.compare(s.length(), t1.length());
            }
        };

        List<String> names = new ArrayList<>();
        names.add("Johar");
        names.add("Lynn");
        names.add("Jin");
        byLengthOrdering.sortedCopy(names).stream().forEach(System.out::println);
    }
}