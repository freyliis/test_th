package com.thoughtworks.test.parser;

import com.thoughtworks.test.number.IntergalacticUnitToRomanNumberParser;
import com.thoughtworks.test.number.IntergalacticUnitToRomanNumbersMap;
import com.thoughtworks.test.number.RomanNumber;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class IntergalacticUnitToRomanNumberParserTest {

    @Test
    public void shouldParseGlobAsI() {
        IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap = new IntergalacticUnitToRomanNumbersMap();
        String glob = "glob";
        IntergalacticUnitToRomanNumberParser objectUnderTest = new IntergalacticUnitToRomanNumberParser(intergalacticUnitToRomanNumbersMap);
        boolean result = objectUnderTest.parse("glob is I");
        assertTrue(result);
        assertThat(intergalacticUnitToRomanNumbersMap.getRomanNumberForIntergalacticUnit(glob).get(), CoreMatchers.is(RomanNumber.I));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotParseGlobAsK() {
        IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap = new IntergalacticUnitToRomanNumbersMap();
        String glob = "glob";
        IntergalacticUnitToRomanNumberParser objectUnderTest = new IntergalacticUnitToRomanNumberParser(intergalacticUnitToRomanNumbersMap);
        boolean result = objectUnderTest.parse(glob + " is K");
        assertFalse(result);
    }

}