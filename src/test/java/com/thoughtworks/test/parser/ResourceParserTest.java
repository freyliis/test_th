package com.thoughtworks.test.parser;

import com.thoughtworks.test.IntergalacticUnitToRomanNumbersMap;
import com.thoughtworks.test.RomanNumber;
import com.thoughtworks.test.RomanNumberCalculator;
import com.thoughtworks.test.resources.ResourcesInMemory;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ResourceParserTest {

    private IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap = new IntergalacticUnitToRomanNumbersMap();
    private ResourcesInMemory resourcesRepository = new ResourcesInMemory();
    private RomanNumberCalculator romanNumberCalculator = new RomanNumberCalculator();

    @Test
    public void shouldParseGlobGlobSilverIs34CreditsToSilverWithPrice() {
        intergalacticUnitToRomanNumbersMap = new IntergalacticUnitToRomanNumbersMap();
        intergalacticUnitToRomanNumbersMap.addIntergalacticUnitToRomanNumber("glob", RomanNumber.I);
        ResourceParser objectUnderTest = new ResourceParser(intergalacticUnitToRomanNumbersMap, resourcesRepository, romanNumberCalculator);
        objectUnderTest.parse("glob glob Silver is 34 Credits");
        assertThat(resourcesRepository.getResourceByName("Silver").get().getPrice(), is(17d));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionDueToMissingIntergalacticUnit() {
        intergalacticUnitToRomanNumbersMap = new IntergalacticUnitToRomanNumbersMap();
        intergalacticUnitToRomanNumbersMap.addIntergalacticUnitToRomanNumber("glob", RomanNumber.I);
        ResourceParser objectUnderTest = new ResourceParser(intergalacticUnitToRomanNumbersMap, resourcesRepository, romanNumberCalculator);
        objectUnderTest.parse("bub is Silver 34 Credits");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionDueToWrongNumberOfCredits() {
        intergalacticUnitToRomanNumbersMap = new IntergalacticUnitToRomanNumbersMap();
        intergalacticUnitToRomanNumbersMap.addIntergalacticUnitToRomanNumber("glob", RomanNumber.I);
        ResourceParser objectUnderTest = new ResourceParser(intergalacticUnitToRomanNumbersMap, resourcesRepository, romanNumberCalculator);
        objectUnderTest.parse("glob is Silver bub Credits");
    }


}