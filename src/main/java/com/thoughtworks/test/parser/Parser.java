package com.thoughtworks.test.parser;

import com.thoughtworks.test.*;
import com.thoughtworks.test.resources.ResourcesRepository;

import java.util.*;

public class Parser {

    private List<ReadParser> parsers = new ArrayList<>();
    private Map<String, String> results = new HashMap<>();


    public Parser(IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap, ResourcesRepository resourcesRepository, RomanNumberCalculator romanNumberCalculator) {
        parsers.add(new HowMuchQuestionParser(romanNumberCalculator, intergalacticUnitToRomanNumbersMap, results));
        parsers.add(new ResourceParser(intergalacticUnitToRomanNumbersMap, resourcesRepository, romanNumberCalculator));
        parsers.add(new IntergalacticUnitToRomanNumberParser(intergalacticUnitToRomanNumbersMap));
    }

    public void parse(String inputText) {
        if (inputText == null || inputText.isEmpty()) {
            throw new IllegalArgumentException();
        }
        for (ReadParser parser : parsers) {
            try {
                if (parser.parse(inputText))
                    break;
            } catch (ParserException e) {
                e.printStackTrace();
            }
        }
    }

    public Map<String, String> getResults() {
        return Collections.unmodifiableMap(results);
    }
}
