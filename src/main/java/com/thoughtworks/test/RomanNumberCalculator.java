package com.thoughtworks.test;

public class RomanNumberCalculator {
    public int calculate(RomanNumber... romanNumbers) {
        int sum = 0;
        int repeatedNumber = 1;
        RomanNumber previousNumber = null;
        for (int i = 0; i < romanNumbers.length; i++) {
            RomanNumber currentNumber = romanNumbers[i];

            if (currentNumber.equals(previousNumber)) {
                repeatedNumber++;
                if (repeatedNumber > 3) {
                    throw new IllegalArgumentException();
                }
            } else {
                repeatedNumber = 1;
            }
            if (previousNumber != null && currentNumber.getValue() > previousNumber.getValue()) {
                sum += (currentNumber.getValue() - 2 * previousNumber.getValue());
            } else {
                sum += currentNumber.getValue();
            }
            previousNumber = currentNumber;
        }

//        sum = getSum(sum, lastParsedRomanNumber, romanNumbers);
        return sum;
    }


    private int getSum(int sum, RomanNumber[] romanNumbers) {
        RomanNumber lastParsedRomanNumber = null;
        for (int i = romanNumbers.length - 1; i >= 0; ) {
            RomanNumber currentNumber = romanNumbers[i];
            if (lastParsedRomanNumber != null && currentNumber.getValue() < lastParsedRomanNumber.getValue()) {
                throw new IllegalArgumentException();
            }
//            if(romanNumber.getValue().equals())
            if (i - 1 < 0) {
                sum += currentNumber.getValue();
                break;
            } else {
                RomanNumber numberBeforeCurrent = romanNumbers[i - 1];
                if (numberBeforeCurrent.getValue() < currentNumber.getValue()) {
                    lastParsedRomanNumber = numberBeforeCurrent;
                    sum += currentNumber.getValue() - numberBeforeCurrent.getValue();
                    i = i - 2;
                } else {
                    lastParsedRomanNumber = currentNumber;
                    sum += currentNumber.getValue();
                    i = i - 1;
                }

            }
        }
        return sum;
    }
}
