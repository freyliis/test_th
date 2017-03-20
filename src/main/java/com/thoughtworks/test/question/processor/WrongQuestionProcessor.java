package com.thoughtworks.test.question.processor;

import static com.thoughtworks.test.configuration.DefaultConfiguration.MESSAGE;


public class WrongQuestionProcessor implements QuestionProcessor {
    @Override
    public String answerQuestion(String question) {
        return MESSAGE;
    }
}
