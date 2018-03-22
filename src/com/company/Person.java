package com.company;

public class Person {
    private String name;
    private static int age;

    public static void old() {
        age++;
    }
    Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name+" "+age+" years old";
    }
}
