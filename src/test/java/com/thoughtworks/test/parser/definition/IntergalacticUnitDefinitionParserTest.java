package com.thoughtworks.test.parser.definition;

import com.thoughtworks.test.definition.intergalacticunit.IntergalacticUnitDictionary;
import com.thoughtworks.test.parser.ParserException;
import com.thoughtworks.test.romannumber.RomanNumber;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.*;

public class IntergalacticUnitDefinitionParserTest {

    @Test
    public void shouldParseGlobAsI() throws ParserException {
        IntergalacticUnitDictionary intergalacticUnitDictionary = new IntergalacticUnitDictionary();
        String glob = "glob";
        IntergalacticUnitDefinitionParser objectUnderTest = new IntergalacticUnitDefinitionParser(intergalacticUnitDictionary);
        boolean result = objectUnderTest.parse("glob is I");
        assertTrue(result);
        assertThat(intergalacticUnitDictionary.getDefinitionByKey(glob).get().getRomanNumber(), CoreMatchers.is(RomanNumber.I));
    }

    @Test(expected = ParserException.class)
    public void shouldNotParseGlobAsK() throws ParserException {
        IntergalacticUnitDictionary intergalacticUnitDictionary = new IntergalacticUnitDictionary();
        String glob = "glob";
        IntergalacticUnitDefinitionParser objectUnderTest = new IntergalacticUnitDefinitionParser(intergalacticUnitDictionary);
        boolean result = objectUnderTest.parse(glob + " is K");
        assertFalse(result);
    }

    @Test
    public void shouldNotParseGlobIsVV() throws ParserException {
        IntergalacticUnitDictionary intergalacticUnitDictionary = new IntergalacticUnitDictionary();
        String glob = "glob";
        IntergalacticUnitDefinitionParser objectUnderTest = new IntergalacticUnitDefinitionParser(intergalacticUnitDictionary);
        boolean result = objectUnderTest.parse(glob + " is VV");
        assertFalse(result);
    }

}