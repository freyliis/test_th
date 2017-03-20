package com.thoughtworks.test.parser.engine.impl;

import com.thoughtworks.test.parser.engine.impl.DefaultParserEngine;
import org.junit.Test;

public class DefaultParserEngineTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionIfParsersListIsNull(){
        new DefaultParserEngine(null);
    }

}