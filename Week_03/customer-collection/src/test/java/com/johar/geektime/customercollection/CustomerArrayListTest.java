package com.johar.geektime.customercollection;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;


class CustomerArrayListTest {

    private CustomerArrayList<String> customerArrayList;

    @BeforeEach
    void init(){
        customerArrayList = new CustomerArrayList<>();
        customerArrayList.add("aaa");
        customerArrayList.add("bbb");
        customerArrayList.add("ccc");
    }

    @Test
    void size() {
        CustomerArrayList<String> arrayList = new CustomerArrayList<>();
        assertThat(arrayList.size()).isEqualTo(0);
        arrayList.add("hello");
        arrayList.add("world");
        assertThat(arrayList.size()).isEqualTo(2);
        arrayList.remove(0);
        assertThat(arrayList.size()).isEqualTo(1);
        arrayList.remove(0);
        assertThat(arrayList.size()).isEqualTo(0);
    }

    @Test
    void isEmpty() {
        CustomerArrayList<String> arrayList = new CustomerArrayList<>();
        assertThat(arrayList.isEmpty()).isEqualTo(true);
        arrayList.add("hello");
        arrayList.add("world");
        assertThat(arrayList.isEmpty()).isEqualTo(false);
        arrayList.remove(0);
        assertThat(arrayList.isEmpty()).isEqualTo(false);
        arrayList.remove(0);
        assertThat(arrayList.isEmpty()).isEqualTo(true);
    }

    @Test
    void contains() {
        CustomerArrayList<String> arrayList = new CustomerArrayList<>();
        assertThat(arrayList.contains("test")).isEqualTo(false);
        arrayList.add("test");
        arrayList.add("OK");
        assertThat(arrayList.contains("test")).isEqualTo(true);
        assertThat(arrayList.contains("")).isEqualTo(false);
        assertThat(arrayList.contains(null)).isEqualTo(false);
    }

    @Test
    void iterator() {
        Iterator<String> stringIterator = customerArrayList.iterator();
        int i = 0;
        while (stringIterator.hasNext()){
            String current = stringIterator.next();
            assertThat(current).isEqualTo(customerArrayList.get(i++));
        }
    }

    @Test
    void toArray() {
    }

    @Test
    void testToArray() {
    }

    @Test
    void add() {
        CustomerArrayList<String> arrayList = new CustomerArrayList();
        arrayList.add(null);
        assertThat(arrayList.get(0)).isEqualTo(null);
        arrayList.add("aaa");
        assertThat(arrayList.get(1)).isEqualTo("aaa");
    }

    @Test
    void remove() {
    }

    @Test
    void containsAll() {
    }

    @Test
    void addAll() {
    }

    @Test
    void testAddAll() {
    }

    @Test
    void clear() {
    }

    @Test
    void get() {
    }

    @Test
    void set() {
    }

    @Test
    void testAdd() {
    }

    @Test
    void testRemove() {
    }

    @Test
    void indexOf() {
    }

    @Test
    void lastIndexOf() {
    }

    @Test
    void subList() {
    }
}