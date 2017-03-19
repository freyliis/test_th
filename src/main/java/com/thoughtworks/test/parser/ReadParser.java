package com.thoughtworks.test.parser;

public interface ReadParser {

    String SEPARATOR = " ";
    String IS_REGEX = SEPARATOR + "is" + SEPARATOR;
    String CREDITS = "Credits";
    String HOW_MUCH_IS_REGEX = "how much" + IS_REGEX;
    String HOW_MANY_IS_REGEX = "how many Credits" + IS_REGEX;


    boolean parse(String inputText) throws ParserException;
}
