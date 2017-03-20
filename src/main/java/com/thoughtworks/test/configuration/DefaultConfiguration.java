package com.thoughtworks.test.configuration;

import com.thoughtworks.test.number.DefaultRomanNumberCalculator;
import com.thoughtworks.test.number.IntergalacticUnitToRomanNumbersMap;
import com.thoughtworks.test.number.RomanNumberCalculator;
import com.thoughtworks.test.parser.DefaultParserEngine;
import com.thoughtworks.test.parser.ReadParser;
import com.thoughtworks.test.parser.definition.ResourceParser;
import com.thoughtworks.test.parser.definition.IntergalacticUnitToRomanNumberParser;
import com.thoughtworks.test.parser.question.QuestionParser;
import com.thoughtworks.test.question.*;
import com.thoughtworks.test.resources.ResourcesInMemory;
import com.thoughtworks.test.resources.ResourcesRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultConfiguration implements Configuration {

    public static final String SEPARATOR = " ";
    public static final String IS = SEPARATOR + "is" + SEPARATOR;
    public static final String CREDITS = "Credits";
    public static final String NUMBER_DEFINITION_REGEX = "^.+" + IS + ".$";
    public static final String RESOURCE_DEFINITION_REGEX = "^.+" + IS + "\\d+" + SEPARATOR + CREDITS + "$";
    public static final String HOW_MUCH_IS_REGEX = "how much" + IS;
    public static final String HOW_MANY_IS_REGEX = "how many " + CREDITS + IS;
    public final static String MESSAGE = "I have no idea what you are talking about";
    private RomanNumberCalculator romanNumberCalculator = new DefaultRomanNumberCalculator();
    private IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap = new IntergalacticUnitToRomanNumbersMap();
    private ResourcesRepository resourcesRepository = new ResourcesInMemory();

    public DefaultParserEngine createParserEngine(QuestionMap questionMap) {
        List<ReadParser> parsers = new ArrayList<>();
        parsers.add(new QuestionParser(HOW_MUCH_IS_REGEX, questionMap));
        parsers.add(new QuestionParser(HOW_MANY_IS_REGEX, questionMap));
        parsers.add(new ResourceParser(intergalacticUnitToRomanNumbersMap, resourcesRepository, romanNumberCalculator));
        parsers.add(new IntergalacticUnitToRomanNumberParser(intergalacticUnitToRomanNumbersMap));
        parsers.add(new QuestionParser("", questionMap));
        return new DefaultParserEngine(parsers);
    }

    public QuestionEngine createQuestionEngine() {
        Map<String, QuestionProcessor> questionProcessorMap = new HashMap<>();
        questionProcessorMap.put(HOW_MUCH_IS_REGEX, new HowMuchQuestionProcessor(romanNumberCalculator, intergalacticUnitToRomanNumbersMap));
        questionProcessorMap.put(HOW_MANY_IS_REGEX, new HowManyQuestionProcessor(romanNumberCalculator, intergalacticUnitToRomanNumbersMap, resourcesRepository));
        questionProcessorMap.put("", new WrongQuestionProcessor());
        return new QuestionEngine(questionProcessorMap);
    }
}
