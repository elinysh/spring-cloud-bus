package com.example.demo;

public interface IntAdder extends Adder<Integer, Integer, Integer> {
    default Integer add(Integer a, Integer b) {
        return a+b;
    }
}
