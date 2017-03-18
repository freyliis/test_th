package com.thoughtworks.test;

public class RomanNumberCalculator {
    public int calculate(RomanNumber... romanNumbers) {
        int sum = 0;
        int repeatedNumberCounter = 1;
        RomanNumber previousNumber = null;
        for (RomanNumber currentNumber : romanNumbers) {
            Integer currentValue = currentNumber.getValue();
            if (currentNumber.isRepeated(previousNumber)) {
                canBeRepeated(repeatedNumberCounter, currentNumber);
                repeatedNumberCounter++;
            } else if (previousNumber != null && currentValue > previousNumber.getValue()) {
                if (repeatedNumberCounter > 1 || !currentNumber.canSubtract(previousNumber)) {
                    throw new IllegalArgumentException();
                }
                sum -= 2 * previousNumber.getValue();
            } else {
                repeatedNumberCounter = 1;
            }
            sum += currentValue;
            previousNumber = currentNumber;
        }
        return sum;
    }

    private void canBeRepeated(int repeatedNumberCounter, RomanNumber currentNumber) {
        if (!currentNumber.canBeRepeated(repeatedNumberCounter)) {
            throw new IllegalArgumentException();
        }
    }
}
