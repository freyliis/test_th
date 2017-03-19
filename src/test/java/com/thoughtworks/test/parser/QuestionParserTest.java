package com.thoughtworks.test.parser;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.thoughtworks.test.parser.ReadParser.HOW_MANY_IS_REGEX;
import static com.thoughtworks.test.parser.ReadParser.HOW_MUCH_IS_REGEX;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class QuestionParserTest {

    private List<String> results;

    @Before
    public void setUp() {
        results = new ArrayList<>();
    }

    @Test
    public void shouldReturnFalseIfQuestionHowMuchIsNotValid() throws ParserException {
        QuestionParser objectUnderTest = new QuestionParser(HOW_MUCH_IS_REGEX, results);
        String question = "glob prok Silver";
        boolean result = objectUnderTest.parse(HOW_MANY_IS_REGEX + question + " ?");
        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseIfQuestionHowManyIsNotValid() throws ParserException {
        QuestionParser objectUnderTest = new QuestionParser(HOW_MANY_IS_REGEX, results);
        String question = "pish";
        boolean result = objectUnderTest.parse(HOW_MUCH_IS_REGEX + question + " ?");
        assertFalse(result);
    }

    @Test
    public void shouldReturnTrueIfQuestionHowMuchIsValid() throws ParserException {
        QuestionParser objectUnderTest = new QuestionParser(HOW_MUCH_IS_REGEX, results);
        String question = "pish";
        boolean result = objectUnderTest.parse(HOW_MUCH_IS_REGEX + question + " ?");
        assertTrue(result);
        assertThat(results.size(), is(1));
        assertThat(results.get(0), is(question));
    }

    @Test
    public void shouldReturnTrueIfQuestionHowManyIsValid() throws ParserException {
        QuestionParser objectUnderTest = new QuestionParser(HOW_MANY_IS_REGEX, results);
        String question = "glob prok Silver";
        boolean result = objectUnderTest.parse(HOW_MANY_IS_REGEX + question + " ?");
        assertTrue(result);
        assertThat(results.size(), is(1));
        assertThat(results.get(0), is(question));
    }
}