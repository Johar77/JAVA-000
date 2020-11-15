package com.johar.jeektime.springbean.domian;

import java.util.Objects;

/**
 * @ClassName: User
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/14 11:41
 * @Since: 1.0.0
 */
public class User {
    private int id;
    private String name;
    private int age;

    public User() {
        this(0);
    }

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static User createUser(){
        User user = new User();
        user.setId(1001);
        user.setName("Anna");
        user.setAge(21);
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}