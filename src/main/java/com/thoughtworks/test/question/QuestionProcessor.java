package com.thoughtworks.test.question;

import com.thoughtworks.test.parser.ParserException;

public interface QuestionProcessor {
    String answerQuestion(String question) throws ParserException;
}
