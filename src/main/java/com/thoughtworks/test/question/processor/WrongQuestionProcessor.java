package com.thoughtworks.test.question.processor;

import com.thoughtworks.test.parser.ParserException;

import static com.thoughtworks.test.configuration.DefaultConfiguration.MESSAGE;


public class WrongQuestionProcessor implements QuestionProcessor {
    @Override
    public String answerQuestion(String question) throws ParserException {
        return MESSAGE;
    }
}
