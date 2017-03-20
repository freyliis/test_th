package com.thoughtworks.test.parser;

import com.thoughtworks.test.definition.DefinitionDictionary;
import com.thoughtworks.test.definition.intergalacticunit.IntergalacticUnit;
import com.thoughtworks.test.definition.number.DefaultRomanNumberCalculator;
import com.thoughtworks.test.definition.intergalacticunit.IntergalacticUnitDictionary;
import com.thoughtworks.test.definition.number.RomanNumber;
import com.thoughtworks.test.definition.resource.Resource;
import com.thoughtworks.test.parser.definition.ResourceParser;
import com.thoughtworks.test.definition.resource.ResourcesDictionary;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ResourceParserTest {

    private DefinitionDictionary<IntergalacticUnit> intergalacticUnitDictionary = new IntergalacticUnitDictionary();
    private DefinitionDictionary<Resource> resourcesRepository = new ResourcesDictionary();
    private DefaultRomanNumberCalculator romanNumberCalculator = new DefaultRomanNumberCalculator();

    @Test
    public void shouldParseGlobGlobSilverIs34CreditsToSilverWithPrice17() {
        intergalacticUnitDictionary = new IntergalacticUnitDictionary();
        intergalacticUnitDictionary.addDefinition(new IntergalacticUnit("glob", RomanNumber.I));
        ResourceParser objectUnderTest = new ResourceParser(intergalacticUnitDictionary, resourcesRepository, romanNumberCalculator);
        boolean result = objectUnderTest.parse("glob glob Silver is 34 Credits");
        assertTrue(result);
        assertThat(resourcesRepository.getDefinitionByKey("Silver").get().getPrice(), is(17d));
    }

    @Test
    public void shouldParsePishPishIronIs3910CreditsToIronWithPrice() {
        intergalacticUnitDictionary = new IntergalacticUnitDictionary();
        intergalacticUnitDictionary.addDefinition(new IntergalacticUnit("pish", RomanNumber.X));
        ResourceParser objectUnderTest = new ResourceParser(intergalacticUnitDictionary, resourcesRepository, romanNumberCalculator);
        boolean result = objectUnderTest.parse("pish pish Iron is 3910 Credits");
        assertTrue(result);
        assertThat(resourcesRepository.getDefinitionByKey("Iron").get().getPrice(), is(195.5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionDueToMissingIntergalacticUnit() {
        intergalacticUnitDictionary = new IntergalacticUnitDictionary();
        intergalacticUnitDictionary.addDefinition(new IntergalacticUnit("glob", RomanNumber.I));
        ResourceParser objectUnderTest = new ResourceParser(intergalacticUnitDictionary, resourcesRepository, romanNumberCalculator);
        objectUnderTest.parse("bub Silver is 34 Credits");
    }

    @Test
    public void shouldParseResourceWithMissingUnit() {
        intergalacticUnitDictionary = new IntergalacticUnitDictionary();
        intergalacticUnitDictionary.addDefinition(new IntergalacticUnit("glob", RomanNumber.I));
        ResourceParser objectUnderTest = new ResourceParser(intergalacticUnitDictionary, resourcesRepository, romanNumberCalculator);
        boolean result = objectUnderTest.parse("glob bub Silver is 34 Credits");
        assertTrue(result);
    }

    @Test
    public void shouldThrowAnExceptionDueToWrongNumberOfCredits() {
        intergalacticUnitDictionary = new IntergalacticUnitDictionary();
        intergalacticUnitDictionary.addDefinition(new IntergalacticUnit("glob", RomanNumber.I));
        ResourceParser objectUnderTest = new ResourceParser(intergalacticUnitDictionary, resourcesRepository, romanNumberCalculator);
        boolean result = objectUnderTest.parse("glob Silver is bub Credits");
        assertFalse(result);
    }
}