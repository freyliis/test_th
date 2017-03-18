package com.thoughtworks.test.parser;

import com.thoughtworks.test.Resource;
import com.thoughtworks.test.RomanNumber;
import com.thoughtworks.test.RomanNumberCalculator;

import java.util.*;

public class Parser {

    public static final String CREDITS = "Credits";
    private Map<String, RomanNumber> intergalacticUnitsToRomanNumbers = new HashMap<>();
    private RomanNumberCalculator romanNumberCalculator = new RomanNumberCalculator();
    private Map<String, Resource> resources = new HashMap<>();

    public void parse(String inputText) {
        if (inputText == null || inputText.isEmpty()) {
            throw new IllegalArgumentException();
        }
        String[] split = inputText.split("is");
        if (split[1].endsWith(CREDITS)) {

            List<RomanNumber> romanNumbersInSequence = new ArrayList<>();
            String[] numbersAndMetal = split[0].split("\\s");
            int i = 0;
            for (; i < numbersAndMetal.length; i++) {
                String text = numbersAndMetal[i];
                if (intergalacticUnitsToRomanNumbers.containsKey(text)) {
                    romanNumbersInSequence.add(intergalacticUnitsToRomanNumbers.get(text));
                } else {
                    break;
                }
            }
            double totalPrice = Double.parseDouble(split[1].replace(CREDITS, "").trim());
            double resourcePrice = totalPrice / romanNumberCalculator.calculate(romanNumbersInSequence);
            resources.put(numbersAndMetal[i], new Resource(numbersAndMetal[i], resourcePrice));
        } else {
            String intergalacticUnit = split[0].trim();
            RomanNumber romanNumber = RomanNumber.valueOf(split[1].trim());
            addRomanNumber(intergalacticUnit, romanNumber);
        }
    }


    public void addRomanNumber(String text, RomanNumber romanNumber) {
        intergalacticUnitsToRomanNumbers.put(text, romanNumber);
    }

    public double getPrice(String resourceName) {
        return resources.get(resourceName).getPrice();
    }

    public RomanNumber getRomanNumberFor(String intergalacticUnit) {
        return intergalacticUnitsToRomanNumbers.get(intergalacticUnit);
    }
}
