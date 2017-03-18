package com.thoughtworks.test.parser;

import com.thoughtworks.test.IntergalacticUnitToRomanNumbersMap;
import com.thoughtworks.test.RomanNumber;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class IntergalacticUnitToRomanNumberParserTest {

    @Test
    public void shouldParseGlobAsI() {
        IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap = new IntergalacticUnitToRomanNumbersMap();
        String glob = "glob";
        IntergalacticUnitToRomanNumberParser objectUnderTest = new IntergalacticUnitToRomanNumberParser(intergalacticUnitToRomanNumbersMap);
        objectUnderTest.parse(glob + " is I");
        assertThat(intergalacticUnitToRomanNumbersMap.getRomanNumberForIntergalacticUnit(glob).get(), CoreMatchers.is(RomanNumber.I));
    }

}