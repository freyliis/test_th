package com.thoughtworks.test;

import com.thoughtworks.test.inputreader.FileInputReader;
import com.thoughtworks.test.inputreader.InputReader;
import com.thoughtworks.test.number.DefaultRomanNumberCalculator;
import com.thoughtworks.test.number.IntergalacticUnitToRomanNumberParser;
import com.thoughtworks.test.number.IntergalacticUnitToRomanNumbersMap;
import com.thoughtworks.test.number.RomanNumberCalculator;
import com.thoughtworks.test.outputwriter.ConsoleOutputWriter;
import com.thoughtworks.test.outputwriter.OutputWriter;
import com.thoughtworks.test.parser.Parser;
import com.thoughtworks.test.parser.ReadParser;
import com.thoughtworks.test.question.*;
import com.thoughtworks.test.resources.ResourceParser;
import com.thoughtworks.test.resources.ResourcesInMemory;
import com.thoughtworks.test.resources.ResourcesRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultRunner implements Runner {

    private RomanNumberCalculator romanNumberCalculator = new DefaultRomanNumberCalculator();
    private IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap = new IntergalacticUnitToRomanNumbersMap();
    private ResourcesRepository resourcesRepository = new ResourcesInMemory();
    private InputReader inputReader = new FileInputReader();
    private OutputWriter outputWriter = new ConsoleOutputWriter();
    private QuestionMap questionMap = new QuestionMap();
    private Logger logger = new Logger(DefaultRunner.class.getName());

    public void run(String source) {
        try {
            String inputText = inputReader.readInput(source);
            getParser().parse(inputText);
            List<String> processedQuestions = getQuestionsProcessor().processQuestions(questionMap);
            outputWriter.writeOutput(processedQuestions);
        } catch (IOException e) {
            logger.log(e.getLocalizedMessage());
        }
    }

    private Parser getParser() {
        List<ReadParser> parsers = new ArrayList<>();
        parsers.add(new QuestionParser(ReadParser.HOW_MUCH_IS_REGEX, questionMap));
        parsers.add(new QuestionParser(ReadParser.HOW_MANY_IS_REGEX, questionMap));
        parsers.add(new ResourceParser(intergalacticUnitToRomanNumbersMap, resourcesRepository, romanNumberCalculator));
        parsers.add(new IntergalacticUnitToRomanNumberParser(intergalacticUnitToRomanNumbersMap));
        parsers.add(new QuestionParser("", questionMap));
        return new Parser(parsers);
    }

    private QuestionsProcessor getQuestionsProcessor() {
        Map<String, QuestionProcessor> questionProcessorMap = new HashMap<>();
        questionProcessorMap.put(ReadParser.HOW_MUCH_IS_REGEX, new HowMuchQuestionProcessor(romanNumberCalculator, intergalacticUnitToRomanNumbersMap));
        questionProcessorMap.put(ReadParser.HOW_MANY_IS_REGEX, new HowManyQuestionProcessor(romanNumberCalculator, intergalacticUnitToRomanNumbersMap, resourcesRepository));
        questionProcessorMap.put("", new WrongQuestionProcessor());
        return new QuestionsProcessor(questionProcessorMap);
    }

}
