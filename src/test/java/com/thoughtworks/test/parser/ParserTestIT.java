package com.thoughtworks.test.parser;

import com.thoughtworks.test.DefaultRomanNumberCalculator;
import com.thoughtworks.test.IntergalacticUnitToRomanNumbersMap;
import com.thoughtworks.test.RomanNumberCalculator;
import com.thoughtworks.test.resources.ResourcesInMemory;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParserTestIT {

    private IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap = new IntergalacticUnitToRomanNumbersMap();
    private ResourcesInMemory resourcesRepository = new ResourcesInMemory();
    private RomanNumberCalculator romanNumberCalculator = new DefaultRomanNumberCalculator();

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenInputIsNull() {
        Parser objectUnderTest = new Parser(intergalacticUnitToRomanNumbersMap, resourcesRepository, romanNumberCalculator);
        objectUnderTest.parse(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenInputIsEmpty() {
        Parser objectUnderTest = new Parser(intergalacticUnitToRomanNumbersMap, resourcesRepository, romanNumberCalculator);
        objectUnderTest.parse("");
    }

    @Test
    public void shouldParseHowMuchQuestion() {
        Parser objectUnderTest = new Parser(intergalacticUnitToRomanNumbersMap, resourcesRepository, romanNumberCalculator);
        objectUnderTest.parse("glob is I");
        objectUnderTest.parse("prok is V");
        objectUnderTest.parse("pish is X");
        objectUnderTest.parse("tegj is L");

        objectUnderTest.parse("glob glob Silver is 34 Credits");
        objectUnderTest.parse("glob prok Gold is 57800 Credits");
        objectUnderTest.parse("pish pish Iron is 3910 Credits");

        objectUnderTest.parse(" how much is pish tegj glob glob ?");
        objectUnderTest.parse("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");

        assertThat(objectUnderTest.getQuestions().size(), is(1));
        assertThat(objectUnderTest.getQuestions().get(0), is("pish tegj glob glob"));
    }
}