package com.thoughtworks.test.parser;

public interface ReadParser {

    String SEPARATOR = " ";
    String IS_REGEX = SEPARATOR + "is" + SEPARATOR;
    String CREDITS = "Credits";
    String HOW_MUCH_IS_REGEX = "how much" + IS_REGEX;
    String HOW_MANY_IS_REGEX = "how many " + CREDITS + IS_REGEX;
    String WRONG_QUESTION = "I have no idea what you are talking about";


    boolean parse(String inputText) throws ParserException;
}
