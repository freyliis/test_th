package com.thoughtworks.test.parser;

import com.thoughtworks.test.IntergalacticUnitToRomanNumbersMap;
import com.thoughtworks.test.RomanNumberCalculator;
import com.thoughtworks.test.resources.ResourcesRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Parser {

    private List<ReadParser> parsers = new ArrayList<>();
    private List<String> questions = new ArrayList<>();


    public Parser(IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap, ResourcesRepository resourcesRepository, RomanNumberCalculator romanNumberCalculator) {
        parsers.add(new QuestionParser(ReadParser.HOW_MUCH_IS_REGEX, questions));
        parsers.add(new QuestionParser(ReadParser.HOW_MANY_IS_REGEX, questions));
        parsers.add(new ResourceParser(intergalacticUnitToRomanNumbersMap, resourcesRepository, romanNumberCalculator));
        parsers.add(new IntergalacticUnitToRomanNumberParser(intergalacticUnitToRomanNumbersMap));
    }

    public void parse(String inputText) {
        if (inputText == null || inputText.isEmpty()) {
            throw new IllegalArgumentException();
        }
        for (ReadParser parser : parsers) {
            try {
                if (parser.parse(inputText.trim()))
                    break;
            } catch (ParserException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> getQuestions() {
        return Collections.unmodifiableList(questions);
    }
}
