package com.thoughtworks.test.parser;

import com.thoughtworks.test.parser.question.QuestionParser;
import com.thoughtworks.test.question.DefaultQuestionList;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import static com.thoughtworks.test.configuration.DefaultConfiguration.HOW_MANY_IS_REGEX;
import static com.thoughtworks.test.configuration.DefaultConfiguration.HOW_MUCH_IS_REGEX;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class QuestionParserTest {

    private DefaultQuestionList results;

    @Before
    public void setUp() {
        results = new DefaultQuestionList();
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
        assertThat(results.getSize(), is(1));
        assertThat(results.getQuestions().size(), is(1));
        assertThat(results.getQuestions().get(0).getQuestionText(), is(question));
    }

    @Test
    public void shouldReturnTrueIfQuestionHowManyIsValid() throws ParserException {
        QuestionParser objectUnderTest = new QuestionParser(HOW_MANY_IS_REGEX, results);
        String question = "glob prok Silver";
        boolean result = objectUnderTest.parse(HOW_MANY_IS_REGEX + question + " ?");
        assertTrue(result);
        assertThat(results.getSize(), is(1));
        assertThat(results.getQuestions().size(), is(1));
        assertThat(results.getQuestions().get(0).getQuestionText(), is(question));
    }

    @Test
    public void shouldAddAnyQuestionAsWrong() throws ParserException {
        QuestionParser objectUnderTest = new QuestionParser("", results);
        String anyQuestion = "any question";
        boolean result = objectUnderTest.parse(anyQuestion + " ?");
        assertTrue(result);
        assertThat(results.getSize(), CoreMatchers.is(1));
        assertThat(results.getQuestions().get(0).getQuestionText(), is(anyQuestion));

    }

}