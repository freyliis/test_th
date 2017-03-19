package com.thoughtworks.test.parser;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class HowMuchQuestionParserTest {

    private List<String> results;

    @Before
    public void setUp() {
        results = new ArrayList<>();
    }

    @Test
    public void shouldReturnFalseIfQuestionIsNotValid() throws ParserException {
        HowMuchQuestionParser objectUnderTest = new HowMuchQuestionParser(results);
        boolean result = objectUnderTest.parse("how many Credits is glob prok Silver ?");
        assertFalse(result);
    }

    @Test
    public void shouldReturnTrueAndAddQuestionIfIsValid() throws ParserException {
        HowMuchQuestionParser objectUnderTest = new HowMuchQuestionParser(results);
        objectUnderTest.parse("how much is pish ?");
    }
}