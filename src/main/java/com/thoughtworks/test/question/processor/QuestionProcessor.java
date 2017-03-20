package com.thoughtworks.test.question.processor;

import com.thoughtworks.test.parser.ParserException;

public interface QuestionProcessor {
    String answerQuestion(String question) throws ParserException;
}
