package com.thoughtworks.test.parser;

import com.thoughtworks.test.RomanNumber;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenInputIsNull() {
        Parser objectUnderTest = new Parser();
        objectUnderTest.parse(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenInputIsEmpty() {
        Parser objectUnderTest = new Parser();
        objectUnderTest.parse("");
    }

    @Test
    public void shouldParseGlobAsI() {
        String glob = "glob";
        Parser objectUnderTest = new Parser();
        objectUnderTest.parse(glob + " is I");
        assertThat(objectUnderTest.getRomanNumberFor(glob), is(RomanNumber.I));
    }

    @Test
    public void shouldParseGlobGlobSilverIs34CreditsToSilverWithPrice() {
        Parser objectUnderTest = new Parser();
        objectUnderTest.addRomanNumber("glob", RomanNumber.I);
        objectUnderTest.parse("glob glob Silver is 34 Credits");
        assertThat(objectUnderTest.getPrice("Silver"), is(17d));
    }

}