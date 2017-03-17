package com.thoughtworks.test;

public enum RomanNumber {
    I(1, true), V(5, false), X(10, true), L(50, false), C(100, true), D(500, false), M(1000, true);

    private Integer value;
    private boolean repeatable;


    RomanNumber(int value, boolean repeatable) {
        this.value = value;
        this.repeatable = repeatable;
    }

    public Integer getValue() {
        return value;
    }

    public boolean isRepeatable() {
        return repeatable;
    }
}
