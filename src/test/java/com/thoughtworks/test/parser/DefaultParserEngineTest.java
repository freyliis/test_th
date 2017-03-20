package com.thoughtworks.test.parser;

import org.junit.Test;

public class DefaultParserEngineTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionIfParsersListIsNull(){
        new DefaultParserEngine(null);
    }

}