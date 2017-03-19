package com.thoughtworks.test.resources;

import com.thoughtworks.test.number.IntergalacticUnitToRomanNumbersMap;
import com.thoughtworks.test.number.RomanNumber;
import com.thoughtworks.test.number.RomanNumberCalculator;
import com.thoughtworks.test.parser.ReadParser;
import com.thoughtworks.test.resources.Resource;
import com.thoughtworks.test.resources.ResourcesRepository;

import java.util.Arrays;
import java.util.List;

public class ResourceParser implements ReadParser {

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
            String[] numbersAndResourceName = split[0].split(SEPARATOR);
            List<RomanNumber> romanNumbersInSequence = intergalacticUnitToRomanNumbersMap.readRomanNumbers(numbersAndResourceName);
            String resourceName = parseResourceName(numbersAndResourceName, romanNumbersInSequence.size());
            double totalPrice = getTotalPrice(split[1]);
            double resourcePrice = totalPrice / romanNumberCalculator.calculate(romanNumbersInSequence);
            resourcesRepository.addResource(new Resource(resourceName, resourcePrice));
            return true;
        }
        return false;
    }

    private String parseResourceName(String[] numbersAndResourceName, int indexOfResourceName) {
        return String.join(SEPARATOR, Arrays.copyOfRange(numbersAndResourceName, indexOfResourceName, numbersAndResourceName.length));
    }

    private double getTotalPrice(String inputText) {
        return Double.parseDouble(inputText.replace(CREDITS, "").trim());
    }
}
