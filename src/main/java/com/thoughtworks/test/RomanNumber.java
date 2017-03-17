package com.thoughtworks.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum RomanNumber {
    I(1, true, Collections.emptyList()),
    V(5, false, Arrays.asList(new RomanNumber[]{RomanNumber.I})),
    X(10, true, Arrays.asList(new RomanNumber[]{RomanNumber.I})),
    L(50, false, Arrays.asList(new RomanNumber[]{RomanNumber.X})),
    C(100, true, Arrays.asList(new RomanNumber[]{RomanNumber.X})),
    D(500, false, Arrays.asList(new RomanNumber[]{RomanNumber.C})),
    M(1000, true, Arrays.asList(new RomanNumber[]{RomanNumber.C}));

    private Integer value;
    private boolean repeatable;
    private List<RomanNumber> properSubtractionNumbers;


    RomanNumber(int value, boolean repeatable, List<RomanNumber> properSubtractionNumbers) {
        this.value = value;
        this.repeatable = repeatable;
        this.properSubtractionNumbers = properSubtractionNumbers;
    }

    public Integer getValue() {
        return value;
    }

    public boolean isRepeatable() {
        return repeatable;
    }

    public boolean isRepeated(RomanNumber romanNumber) {
        return this.equals(romanNumber);
    }

    public boolean canSubtract(RomanNumber romanNumber) {
        return this.properSubtractionNumbers.contains(romanNumber);
    }
}
