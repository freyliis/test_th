package com.thoughtworks.test.parser;

public interface ReadParser {

    String SEPARATOR = " ";
    String IS_REGEX = SEPARATOR + "is" + SEPARATOR;


    boolean parse(String inputText) throws ParserException;
}
