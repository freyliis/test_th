package com.thoughtworks.test.definition.intergalacticunit;

import com.thoughtworks.test.romannumber.RomanNumber;
import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class IntergalacticUnitDictionaryTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowParseExceptionWhenAddingTheSameUnitWithDifferentValue() {
        IntergalacticUnitDictionary objectUnderTest = new IntergalacticUnitDictionary();
        objectUnderTest.addDefinition(new IntergalacticUnit("piri", RomanNumber.C));
        objectUnderTest.addDefinition(new IntergalacticUnit("piri", RomanNumber.I));
    }

    @Test
    public void shouldAddFirstRomanNumberWhenAddingDifferentUnitTheSameNumber() {
        IntergalacticUnitDictionary objectUnderTest = new IntergalacticUnitDictionary();
        objectUnderTest.addDefinition(new IntergalacticUnit("piri", RomanNumber.C));
        objectUnderTest.addDefinition(new IntergalacticUnit("bub", RomanNumber.C));
        Optional<IntergalacticUnit> result = objectUnderTest.getDefinitionByKey("piri");
        assertTrue(result.isPresent());
        result = objectUnderTest.getDefinitionByKey("bub");
        assertFalse(result.isPresent());

    }
}