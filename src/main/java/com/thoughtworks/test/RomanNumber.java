package com.thoughtworks.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum RomanNumber {
    I(1, 3, Collections.emptyList()),
    V(5, 1, Arrays.asList(new RomanNumber[]{RomanNumber.I})),
    X(10, 3, Arrays.asList(new RomanNumber[]{RomanNumber.I})),
    L(50, 1, Arrays.asList(new RomanNumber[]{RomanNumber.X})),
    C(100, 3, Arrays.asList(new RomanNumber[]{RomanNumber.X})),
    D(500, 1, Arrays.asList(new RomanNumber[]{RomanNumber.C})),
    M(1000, 3, Arrays.asList(new RomanNumber[]{RomanNumber.C}));

    private Integer value;
    private int repeatLimit;
    private List<RomanNumber> properSubtractionNumbers;


    RomanNumber(int value, int repeatLimit, List<RomanNumber> properSubtractionNumbers) {
        this.value = value;
        this.repeatLimit = repeatLimit;
        this.properSubtractionNumbers = properSubtractionNumbers;
    }

    public Integer getValue() {
        return value;
    }

    public boolean canBeRepeated(int currentRepeatCounter) {
        return this.repeatLimit > currentRepeatCounter;
    }

    public boolean isRepeated(RomanNumber romanNumber) {
        return this.equals(romanNumber);
    }

    public boolean canSubtract(RomanNumber romanNumber) {
        return this.properSubtractionNumbers.contains(romanNumber);
    }
}
