package com.thoughtworks.test.parser;

import com.thoughtworks.test.definition.intergalacticunit.IntergalacticUnitDictionary;
import com.thoughtworks.test.romannumber.RomanNumber;
import com.thoughtworks.test.parser.definition.IntergalacticUnitDefinitionParser;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.*;

public class IntergalacticUnitToRomanNumberParserTest {

    @Test
    public void shouldParseGlobAsI() {
        IntergalacticUnitDictionary intergalacticUnitDictionary = new IntergalacticUnitDictionary();
        String glob = "glob";
        IntergalacticUnitDefinitionParser objectUnderTest = new IntergalacticUnitDefinitionParser(intergalacticUnitDictionary);
        boolean result = objectUnderTest.parse("glob is I");
        assertTrue(result);
        assertThat(intergalacticUnitDictionary.getDefinitionByKey(glob).get().getRomanNumber(), CoreMatchers.is(RomanNumber.I));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotParseGlobAsK() {
        IntergalacticUnitDictionary intergalacticUnitDictionary = new IntergalacticUnitDictionary();
        String glob = "glob";
        IntergalacticUnitDefinitionParser objectUnderTest = new IntergalacticUnitDefinitionParser(intergalacticUnitDictionary);
        boolean result = objectUnderTest.parse(glob + " is K");
        assertFalse(result);
    }

    @Test
    public void shouldNotParseGlobIsVV() {
        IntergalacticUnitDictionary intergalacticUnitDictionary = new IntergalacticUnitDictionary();
        String glob = "glob";
        IntergalacticUnitDefinitionParser objectUnderTest = new IntergalacticUnitDefinitionParser(intergalacticUnitDictionary);
        boolean result = objectUnderTest.parse(glob + " is VV");
        assertFalse(result);
    }

}