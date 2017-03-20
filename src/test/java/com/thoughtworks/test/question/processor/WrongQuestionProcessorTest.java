package com.thoughtworks.test.question.processor;

import org.junit.Test;

import static com.thoughtworks.test.configuration.DefaultConfiguration.MESSAGE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class WrongQuestionProcessorTest {

    @Test
    public void shouldReturnDefaultMessageForAnyQuestion() {
        WrongQuestionProcessor objectUnderTest = new WrongQuestionProcessor();
        String result = objectUnderTest.answerQuestion("any");
        assertThat(result, is(MESSAGE));
    }

    @Test
    public void shouldReturnDefaultMessageForNull() {
        WrongQuestionProcessor objectUnderTest = new WrongQuestionProcessor();
        String result = objectUnderTest.answerQuestion(null);
        assertThat(result, is(MESSAGE));
    }

}