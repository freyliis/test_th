package com.thoughtworks.test.parser;

public interface ReadParser {

    public static final String IS_REGEX = "is";

    boolean parse(String inputText);
}
