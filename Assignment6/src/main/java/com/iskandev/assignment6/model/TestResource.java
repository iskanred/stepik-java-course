package com.iskandev.assignment6.model;

import org.springframework.stereotype.Component;

/** Resource Service */
@Component
public class TestResource {
    private String name;
    private int age;

    public TestResource(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public TestResource() {
        this.name = "";
        this.age = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}