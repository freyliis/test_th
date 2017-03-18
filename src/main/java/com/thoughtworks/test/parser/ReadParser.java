package com.thoughtworks.test.parser;

public interface ReadParser {

    String IS_REGEX = "is";
    String HOW_MUCH_IS_REGEX = "how much is";
    String SEPARATOR = "\\s";

    boolean parse(String inputText) throws ParserException;
}
