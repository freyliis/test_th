package com.thoughtworks.test.configuration.impl;

import com.thoughtworks.test.configuration.Configuration;
import com.thoughtworks.test.definition.DefinitionDictionary;
import com.thoughtworks.test.definition.intergalacticunit.IntergalacticUnitDictionary;
import com.thoughtworks.test.definition.resource.ResourcesDictionary;
import com.thoughtworks.test.parser.ReadParser;
import com.thoughtworks.test.parser.definition.IntergalacticUnitDefinitionParser;
import com.thoughtworks.test.parser.definition.ResourceDefinitionParser;
import com.thoughtworks.test.parser.engine.ParserEngine;
import com.thoughtworks.test.parser.engine.impl.DefaultParserEngine;
import com.thoughtworks.test.parser.question.QuestionParser;
import com.thoughtworks.test.question.DefaultQuestionList;
import com.thoughtworks.test.question.QuestionList;
import com.thoughtworks.test.question.engine.QuestionEngine;
import com.thoughtworks.test.question.engine.impl.DefaultQuestionEngine;
import com.thoughtworks.test.question.processor.QuestionProcessor;
import com.thoughtworks.test.question.processor.impl.HowManyQuestionProcessor;
import com.thoughtworks.test.question.processor.impl.HowMuchQuestionProcessor;
import com.thoughtworks.test.question.processor.impl.WrongQuestionProcessor;
import com.thoughtworks.test.romannumber.DefaultRomanNumberCalculator;
import com.thoughtworks.test.romannumber.RomanNumberCalculator;

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
    private IntergalacticUnitDictionary intergalacticUnitDictionary = new IntergalacticUnitDictionary();
    private DefinitionDictionary resourcesRepository = new ResourcesDictionary();

    public ParserEngine createParserEngine(QuestionList questionMap) {
        List<ReadParser> parsers = new ArrayList<>();
        parsers.add(new QuestionParser(HOW_MUCH_IS_REGEX, questionMap));
        parsers.add(new QuestionParser(HOW_MANY_IS_REGEX, questionMap));
        parsers.add(new ResourceDefinitionParser(intergalacticUnitDictionary, resourcesRepository, romanNumberCalculator));
        parsers.add(new IntergalacticUnitDefinitionParser(intergalacticUnitDictionary));
        parsers.add(new QuestionParser("", questionMap));
        return new DefaultParserEngine(parsers);
    }

    public QuestionEngine createQuestionEngine() {
        Map<String, QuestionProcessor> questionProcessorMap = new HashMap<>();
        questionProcessorMap.put(HOW_MUCH_IS_REGEX, new HowMuchQuestionProcessor(romanNumberCalculator, intergalacticUnitDictionary));
        questionProcessorMap.put(HOW_MANY_IS_REGEX, new HowManyQuestionProcessor(romanNumberCalculator, intergalacticUnitDictionary, resourcesRepository));
        questionProcessorMap.put("", new WrongQuestionProcessor());
        return new DefaultQuestionEngine(questionProcessorMap);
    }

    @Override
    public QuestionList createQuestionList() {
        return new DefaultQuestionList();
    }


}
