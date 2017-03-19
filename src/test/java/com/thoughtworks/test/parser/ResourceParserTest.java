package com.thoughtworks.test.parser;

import com.thoughtworks.test.number.DefaultRomanNumberCalculator;
import com.thoughtworks.test.number.IntergalacticUnitToRomanNumbersMap;
import com.thoughtworks.test.number.RomanNumber;
import com.thoughtworks.test.resources.ResourcesInMemory;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ResourceParserTest {

    private IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap = new IntergalacticUnitToRomanNumbersMap();
    private ResourcesInMemory resourcesRepository = new ResourcesInMemory();
    private DefaultRomanNumberCalculator romanNumberCalculator = new DefaultRomanNumberCalculator();

    @Test
    public void shouldParseGlobGlobSilverIs34CreditsToSilverWithPrice17() {
        intergalacticUnitToRomanNumbersMap = new IntergalacticUnitToRomanNumbersMap();
        intergalacticUnitToRomanNumbersMap.addIntergalacticUnitToRomanNumber("glob", RomanNumber.I);
        ResourceParser objectUnderTest = new ResourceParser(intergalacticUnitToRomanNumbersMap, resourcesRepository, romanNumberCalculator);
        objectUnderTest.parse("glob glob Silver is 34 Credits");
        assertThat(resourcesRepository.getResourceByName("Silver").get().getPrice(), is(17d));
    }

    @Test
    public void shouldParsePishPishIronIs3910CreditsToIronWithPrice() {
        intergalacticUnitToRomanNumbersMap = new IntergalacticUnitToRomanNumbersMap();
        intergalacticUnitToRomanNumbersMap.addIntergalacticUnitToRomanNumber("pish", RomanNumber.X);
        ResourceParser objectUnderTest = new ResourceParser(intergalacticUnitToRomanNumbersMap, resourcesRepository, romanNumberCalculator);
        objectUnderTest.parse("pish pish Iron is 3910 Credits");
        assertThat(resourcesRepository.getResourceByName("Iron").get().getPrice(), is(195.5));
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