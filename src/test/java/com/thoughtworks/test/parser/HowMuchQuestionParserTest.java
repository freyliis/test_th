package com.thoughtworks.test.parser;

import com.thoughtworks.test.IntergalacticUnitToRomanNumbersMap;
import com.thoughtworks.test.RomanNumber;
import com.thoughtworks.test.DefaultRomanNumberCalculator;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class HowMuchQuestionParserTest {

    private DefaultRomanNumberCalculator romanNumberCalculator = new DefaultRomanNumberCalculator();
    private IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap;
    private Map<String, String> results;

    @Before
    public void setUp() {
        results = new HashMap<>();
    }

    @Test
    public void shouldReturnFalseIfQuestionIsNotValid() throws ParserException {
        intergalacticUnitToRomanNumbersMap = new IntergalacticUnitToRomanNumbersMap();
        HowMuchQuestionParser objectUnderTest = new HowMuchQuestionParser(romanNumberCalculator, intergalacticUnitToRomanNumbersMap, results);
        boolean result = objectUnderTest.parse("how many Credits is glob prok Silver ?");
        assertFalse(result);
    }

    @Test(expected = ParserException.class)
    public void shouldThrowAnExceptionIfQuestionIsValidButAnyNumberIsInvalid() throws ParserException {
        intergalacticUnitToRomanNumbersMap = new IntergalacticUnitToRomanNumbersMap();
        HowMuchQuestionParser objectUnderTest = new HowMuchQuestionParser(romanNumberCalculator, intergalacticUnitToRomanNumbersMap, results);
        objectUnderTest.parse("how much is pish ?");
    }

    @Test
    public void shouldReturnTrueIfQuestionIsValid() throws ParserException {
        intergalacticUnitToRomanNumbersMap = new IntergalacticUnitToRomanNumbersMap();
        String question = "pish";
        intergalacticUnitToRomanNumbersMap.addIntergalacticUnitToRomanNumber(question, RomanNumber.C);
        HowMuchQuestionParser objectUnderTest = new HowMuchQuestionParser(romanNumberCalculator, intergalacticUnitToRomanNumbersMap, results);
        boolean result = objectUnderTest.parse("how much is " + question + " ?");
        assertTrue(result);
        assertThat(results.size(), is(1));
        assertThat(results.get(question), is(RomanNumber.C.getValue().toString()));
    }

}