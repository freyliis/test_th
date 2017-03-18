package com.thoughtworks.test.parser;

import com.thoughtworks.test.IntergalacticUnitToRomanNumbersMap;
import com.thoughtworks.test.RomanNumber;
import com.thoughtworks.test.DefaultRomanNumberCalculator;
import com.thoughtworks.test.RomanNumberCalculator;
import com.thoughtworks.test.resources.Resource;
import com.thoughtworks.test.resources.ResourcesRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ResourceParser implements ReadParser {
    public static final String CREDITS = "Credits";
    private IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap;
    private ResourcesRepository resourcesRepository;
    private RomanNumberCalculator romanNumberCalculator;

    public ResourceParser(IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap, ResourcesRepository resourcesRepository, RomanNumberCalculator romanNumberCalculator) {
        this.intergalacticUnitToRomanNumbersMap = intergalacticUnitToRomanNumbersMap;
        this.resourcesRepository = resourcesRepository;
        this.romanNumberCalculator = romanNumberCalculator;
    }

    public boolean parse(String inputText) {
        if (inputText.endsWith(CREDITS)) {
            String[] split = inputText.split(IS_REGEX);
            List<RomanNumber> romanNumbersInSequence = new ArrayList<>();
            String[] numbersAndResourceName = split[0].split(SEPARATOR);
            int indexOfResourceName = parseRomanNumbers(romanNumbersInSequence, numbersAndResourceName);
            String resourceName = parseResourceName(numbersAndResourceName, indexOfResourceName);
            double totalPrice = getTotalPrice(split[1]);
            double resourcePrice = totalPrice / romanNumberCalculator.calculate(romanNumbersInSequence);
            resourcesRepository.addResource(new Resource(resourceName, resourcePrice));
            return true;
        }
        return false;
    }

    private String parseResourceName(String[] numbersAndResourceName, int indexOfResourceName) {
        return String.join("\\s", Arrays.copyOfRange(numbersAndResourceName, indexOfResourceName, numbersAndResourceName.length));
    }

    private int parseRomanNumbers(List<RomanNumber> romanNumbersInSequence, String[] numbersAndResourceName) {
        int i = 0;
        for (; i < numbersAndResourceName.length; i++) {
            String text = numbersAndResourceName[i];
            Optional<RomanNumber> romanNumberForIntergalacticUnit = intergalacticUnitToRomanNumbersMap.getRomanNumberForIntergalacticUnit(text);
            if (romanNumberForIntergalacticUnit.isPresent()) {
                romanNumbersInSequence.add(romanNumberForIntergalacticUnit.get());
            } else {
                break;
            }
        }
        return i;
    }

    private double getTotalPrice(String inputText) {
        return Double.parseDouble(inputText.replace(CREDITS, "").trim());
    }
}
