package com.thoughtworks.test.parser;

import com.thoughtworks.test.*;
import com.thoughtworks.test.resources.ResourcesRepository;

import java.util.*;

public class Parser {


    private RomanNumberCalculator romanNumberCalculator = new RomanNumberCalculator();
    private final IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap;
    private final ResourcesRepository resourcesRepository;
    private List<ReadParser> parsers = new ArrayList<>();


    public Parser(IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap, ResourcesRepository resourcesRepository) {
        this.intergalacticUnitToRomanNumbersMap = intergalacticUnitToRomanNumbersMap;
        this.resourcesRepository = resourcesRepository;
        parsers.add(new IntergalacticUnitToRomanNumberParser(intergalacticUnitToRomanNumbersMap));
        parsers.add(new ResourceParser(intergalacticUnitToRomanNumbersMap, resourcesRepository, new RomanNumberCalculator()));
    }

    public void parse(String inputText) {
        if (inputText == null || inputText.isEmpty()) {
            throw new IllegalArgumentException();
        }
        for (ReadParser parser : parsers) {
            parser.parse(inputText);
        }

    }
}
