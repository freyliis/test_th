package com.thoughtworks.test.question;

import com.thoughtworks.test.parser.ParserException;

import static com.thoughtworks.test.parser.ParserException.MESSAGE;

public class WrongQuestionProcessor implements QuestionProcessor {
    @Override
    public String answerQuestion(String question) throws ParserException {
        return MESSAGE;
    }
}
