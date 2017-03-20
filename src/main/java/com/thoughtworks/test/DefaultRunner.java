package com.thoughtworks.test;

import com.thoughtworks.test.configuration.Configuration;
import com.thoughtworks.test.configuration.DefaultConfiguration;
import com.thoughtworks.test.inputreader.FileInputReader;
import com.thoughtworks.test.outputwriter.ConsoleOutputWriter;
import com.thoughtworks.test.outputwriter.OutputWriter;
import com.thoughtworks.test.question.QuestionMap;

import java.util.List;

public class DefaultRunner implements Runner {

    private Configuration configuration = new DefaultConfiguration();
    private OutputWriter outputWriter = new ConsoleOutputWriter();
    private QuestionMap questionMap = new QuestionMap();

    public void run(String source) {
        configuration.getParserEngine(questionMap).parseInput(new FileInputReader(source));
        List<String> processedQuestions = configuration.getQuestionsProcessor().processQuestions(questionMap);
        outputWriter.writeOutput(processedQuestions);
    }
}
