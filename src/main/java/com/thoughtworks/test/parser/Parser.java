package com.thoughtworks.test.parser;

import com.thoughtworks.test.*;
import com.thoughtworks.test.resources.Resource;
import com.thoughtworks.test.resources.ResourcesInMemory;
import com.thoughtworks.test.resources.ResourcesRepository;

import java.util.*;

public class Parser {

    public static final String CREDITS = "Credits";

    private RomanNumberCalculator romanNumberCalculator = new RomanNumberCalculator();
    private final IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap;
    private final ResourcesRepository resourcesRepository;
    private final IntergalacticUnitToRomanNumberParser intergalacticUnitToRomanNumberParser;


    public Parser(IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap, ResourcesRepository resourcesRepository) {
        this.intergalacticUnitToRomanNumbersMap = intergalacticUnitToRomanNumbersMap;
        this.resourcesRepository = resourcesRepository;
        this.intergalacticUnitToRomanNumberParser = new IntergalacticUnitToRomanNumberParser(intergalacticUnitToRomanNumbersMap);
    }

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
                Optional<RomanNumber> romanNumberForIntergalacticUnit = intergalacticUnitToRomanNumbersMap.getRomanNumberForIntergalacticUnit(text);
                if (romanNumberForIntergalacticUnit.isPresent()) {
                    romanNumbersInSequence.add(romanNumberForIntergalacticUnit.get());
                } else {
                    break;
                }
            }
            double totalPrice = Double.parseDouble(split[1].replace(CREDITS, "").trim());
            double resourcePrice = totalPrice / romanNumberCalculator.calculate(romanNumbersInSequence);
            resourcesRepository.addResource(new Resource(numbersAndMetal[i], resourcePrice));
        } else {
            intergalacticUnitToRomanNumberParser.parse(inputText);
        }
    }
}
