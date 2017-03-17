package com.thoughtworks.test;

public class RomanNumberCalculator {
    public int calculate(RomanNumber... romanNumbers) {
        int sum = 0;
        for (RomanNumber romanNumber : romanNumbers) {

            sum += romanNumber.getValue();
        }
        return sum;
    }
}
