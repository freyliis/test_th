package com.thoughtworks.test.parser.definition;

import com.thoughtworks.test.definition.DefinitionDictionary;
import com.thoughtworks.test.definition.intergalacticunit.IntergalacticUnit;
import com.thoughtworks.test.definition.intergalacticunit.IntergalacticUnitDictionary;
import com.thoughtworks.test.definition.resource.Resource;
import com.thoughtworks.test.definition.resource.ResourcesDictionary;
import com.thoughtworks.test.parser.ParserException;
import com.thoughtworks.test.romannumber.DefaultRomanNumberCalculator;
import com.thoughtworks.test.romannumber.RomanNumber;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ResourceDefinitionParserTest {

    private DefinitionDictionary<IntergalacticUnit> intergalacticUnitDictionary = new IntergalacticUnitDictionary();
    private DefinitionDictionary<Resource> resourcesRepository = new ResourcesDictionary();
    private DefaultRomanNumberCalculator romanNumberCalculator = new DefaultRomanNumberCalculator();

    @Test
    public void shouldParseGlobGlobSilverIs34CreditsToSilverWithPrice17() throws ParserException {
        intergalacticUnitDictionary = new IntergalacticUnitDictionary();
        intergalacticUnitDictionary.addDefinition(new IntergalacticUnit("glob", RomanNumber.I));
        ResourceDefinitionParser objectUnderTest = new ResourceDefinitionParser(intergalacticUnitDictionary, resourcesRepository, romanNumberCalculator);
        boolean result = objectUnderTest.parse("glob glob Silver is 34 Credits");
        assertTrue(result);
        assertThat(resourcesRepository.getDefinitionByKey("Silver").get().getPrice(), is(17d));
    }

    @Test
    public void shouldParsePishPishIronIs3910CreditsToIronWithPrice() throws ParserException {
        intergalacticUnitDictionary = new IntergalacticUnitDictionary();
        intergalacticUnitDictionary.addDefinition(new IntergalacticUnit("pish", RomanNumber.X));
        ResourceDefinitionParser objectUnderTest = new ResourceDefinitionParser(intergalacticUnitDictionary, resourcesRepository, romanNumberCalculator);
        boolean result = objectUnderTest.parse("pish pish Iron is 3910 Credits");
        assertTrue(result);
        assertThat(resourcesRepository.getDefinitionByKey("Iron").get().getPrice(), is(195.5));
    }

    @Test(expected = ParserException.class)
    public void shouldThrowAnExceptionDueToMissingIntergalacticUnit() throws ParserException {
        intergalacticUnitDictionary = new IntergalacticUnitDictionary();
        intergalacticUnitDictionary.addDefinition(new IntergalacticUnit("glob", RomanNumber.I));
        ResourceDefinitionParser objectUnderTest = new ResourceDefinitionParser(intergalacticUnitDictionary, resourcesRepository, romanNumberCalculator);
        objectUnderTest.parse("bub Silver is 34 Credits");
    }

    @Test
    public void shouldParseResourceWithMissingUnit() throws ParserException {
        intergalacticUnitDictionary = new IntergalacticUnitDictionary();
        intergalacticUnitDictionary.addDefinition(new IntergalacticUnit("glob", RomanNumber.I));
        ResourceDefinitionParser objectUnderTest = new ResourceDefinitionParser(intergalacticUnitDictionary, resourcesRepository, romanNumberCalculator);
        boolean result = objectUnderTest.parse("glob bub Silver is 34 Credits");
        assertTrue(result);
    }

    @Test
    public void shouldThrowAnExceptionDueToWrongNumberOfCredits() throws ParserException {
        intergalacticUnitDictionary = new IntergalacticUnitDictionary();
        intergalacticUnitDictionary.addDefinition(new IntergalacticUnit("glob", RomanNumber.I));
        ResourceDefinitionParser objectUnderTest = new ResourceDefinitionParser(intergalacticUnitDictionary, resourcesRepository, romanNumberCalculator);
        boolean result = objectUnderTest.parse("glob Silver is bub Credits");
        assertFalse(result);
    }
}