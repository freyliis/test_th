package com.thoughtworks.test.question.processor.impl;

import com.thoughtworks.test.question.processor.QuestionProcessor;

import static com.thoughtworks.test.configuration.impl.DefaultConfiguration.MESSAGE;


public class WrongQuestionProcessor implements QuestionProcessor {
    @Override
    public String answerQuestion(String question) {
        return MESSAGE;
    }
}
