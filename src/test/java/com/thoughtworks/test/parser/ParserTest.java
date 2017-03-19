package com.thoughtworks.test.parser;

import org.junit.Test;

public class ParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenInputIsNull() {
        Parser objectUnderTest = new Parser(null);
        objectUnderTest.parse(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenInputIsEmpty() {
        Parser objectUnderTest = new Parser(null);
        objectUnderTest.parse("");
    }


}