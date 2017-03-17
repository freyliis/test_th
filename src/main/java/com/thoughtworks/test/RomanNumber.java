package com.thoughtworks.test;

public enum RomanNumber {
    I(1), V(5), X(10), L(50), C(100), D(500), M(1000);

    private Integer value;


    RomanNumber(int i) {
        this.value = i;
    }

    public Integer getValue() {
        return value;
    }
}
