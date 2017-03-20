package com.thoughtworks.test;

import com.thoughtworks.test.configuration.Configuration;
import com.thoughtworks.test.configuration.DefaultConfiguration;
import com.thoughtworks.test.inputreader.FileLineByLineInputReader;
import com.thoughtworks.test.inputreader.InputReader;
import com.thoughtworks.test.inputreader.ReadException;
import com.thoughtworks.test.outputwriter.ConsoleOutputWriter;
import com.thoughtworks.test.outputwriter.OutputWriter;
import com.thoughtworks.test.parser.ParserException;
import com.thoughtworks.test.question.DefaultQuestionList;

import java.util.List;

public class DefaultRunner implements Runner {

    private Configuration configuration = new DefaultConfiguration();
    private OutputWriter outputWriter = new ConsoleOutputWriter();
    private DefaultQuestionList questionMap = new DefaultQuestionList();
    private static final Logger LOGGER = new Logger(DefaultRunner.class.getName());

    public void run(String source) {
        try {
            InputReader inputReader = new FileLineByLineInputReader(source);
            configuration.createParserEngine(questionMap).parseInput(inputReader);
            List<String> processedQuestions = configuration.createQuestionEngine().processQuestions(questionMap);
            outputWriter.writeOutput(processedQuestions);
        } catch (ReadException |ParserException e) {
            LOGGER.logError(e.getMessage());
        }

    }
}
