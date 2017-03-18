package com.thoughtworks.test.parser;

import com.thoughtworks.test.IntergalacticUnitToRomanNumbersMap;
import com.thoughtworks.test.resources.ResourcesInMemory;
import com.thoughtworks.test.RomanNumber;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ParserTest {

    private IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap = new IntergalacticUnitToRomanNumbersMap();
    private ResourcesInMemory resourcesRepository = new ResourcesInMemory();

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenInputIsNull() {
        Parser objectUnderTest = new Parser(intergalacticUnitToRomanNumbersMap, resourcesRepository);
        objectUnderTest.parse(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenInputIsEmpty() {
        Parser objectUnderTest = new Parser(intergalacticUnitToRomanNumbersMap, resourcesRepository);
        objectUnderTest.parse("");
    }


    @Test
    public void shouldParseGlobGlobSilverIs34CreditsToSilverWithPrice() {
        intergalacticUnitToRomanNumbersMap = new IntergalacticUnitToRomanNumbersMap();
        intergalacticUnitToRomanNumbersMap.addIntergalacticUnitToRomanNumber("glob", RomanNumber.I);
        Parser objectUnderTest = new Parser(intergalacticUnitToRomanNumbersMap, resourcesRepository);
        objectUnderTest.parse("glob glob Silver is 34 Credits");
        assertThat(resourcesRepository.getResourceByName("Silver").get().getPrice(), is(17d));
    }

}