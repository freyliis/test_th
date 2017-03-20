package com.thoughtworks.test;

import com.thoughtworks.test.configuration.Configuration;
import com.thoughtworks.test.configuration.impl.DefaultConfiguration;
import com.thoughtworks.test.inputreader.impl.FileLineByLineInputReader;
import com.thoughtworks.test.inputreader.InputReader;
import com.thoughtworks.test.inputreader.ReadException;
import com.thoughtworks.test.outputwriter.impl.ConsoleOutputWriter;
import com.thoughtworks.test.outputwriter.OutputWriter;
import com.thoughtworks.test.parser.ParserException;
import com.thoughtworks.test.question.QuestionList;
import com.thoughtworks.test.simplelogger.Logger;

import java.util.List;

public class DefaultRunner implements Runner {

    private Configuration configuration = new DefaultConfiguration();
    private OutputWriter outputWriter = new ConsoleOutputWriter();
    private static final Logger LOGGER = new Logger(DefaultRunner.class.getName());

    public void run(String source) {
        try {
            InputReader inputReader = new FileLineByLineInputReader(source);
            QuestionList questionList = configuration.createQuestionList();
            configuration.createParserEngine(questionList).parseInput(inputReader);
            List<String> processedQuestions = configuration.createQuestionEngine().processQuestions(questionList);
            outputWriter.writeOutput(processedQuestions);
        } catch (ReadException | ParserException e) {
            LOGGER.logError(e.getMessage());
        }
    }
}
