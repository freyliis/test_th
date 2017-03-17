package com.thoughtworks.test;

public class RomanNumberCalculator {
    public int calculate(RomanNumber... romanNumbers) {
        int sum = 0;
        int repeatedNumberCounter = 1;
        RomanNumber previousNumber = null;
        for (RomanNumber currentNumber : romanNumbers) {
            if (currentNumber.isRepeated(previousNumber)) {
                if (!currentNumber.isRepeatable()) {
                    throw new IllegalArgumentException();
                }
                repeatedNumberCounter++;
                if (repeatedNumberCounter > 3) {
                    throw new IllegalArgumentException();
                }
                sum += currentNumber.getValue();
            } else if (previousNumber != null && currentNumber.getValue() > previousNumber.getValue()) {
                if (repeatedNumberCounter > 1 || !currentNumber.canSubtract(previousNumber)) {
                    throw new IllegalArgumentException();
                }
                sum += (currentNumber.getValue() - 2 * previousNumber.getValue());

            } else {
                repeatedNumberCounter = 1;
                sum += currentNumber.getValue();
            }
            previousNumber = currentNumber;
        }
        return sum;
    }
}
