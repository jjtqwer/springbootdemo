package com.example.springbootdemo.entity;

public class T {
    int age;
    String name;

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

    public T(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "T{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public T() {
    }
}
