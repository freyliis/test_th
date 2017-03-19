package com.thoughtworks.test.parser;

public class ParserException extends Exception {

    public final static String MESSAGE = "I have no idea what you are talking about";

    @Override
    public String getMessage() {
        return MESSAGE;
    }

    @Override
    public String getLocalizedMessage() {
        return MESSAGE;
    }
}
