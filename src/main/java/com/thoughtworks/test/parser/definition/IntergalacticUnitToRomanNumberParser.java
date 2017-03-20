package com.thoughtworks.test.parser.definition;

import com.thoughtworks.test.number.IntergalacticUnitToRomanNumbersMap;
import com.thoughtworks.test.number.RomanNumber;
import com.thoughtworks.test.parser.ReadParser;

import static com.thoughtworks.test.configuration.DefaultConfiguration.IS;
import static com.thoughtworks.test.configuration.DefaultConfiguration.NUMBER_DEFINITION_REGEX;

public class IntergalacticUnitToRomanNumberParser implements ReadParser {

    private IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap;

    public IntergalacticUnitToRomanNumberParser(IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap) {
        this.intergalacticUnitToRomanNumbersMap = intergalacticUnitToRomanNumbersMap;
    }

    public boolean parse(String inputText) {
        if (inputText.matches(NUMBER_DEFINITION_REGEX)) {
            String[] split = inputText.split(IS);
            String intergalacticUnit = split[0].trim();
            RomanNumber romanNumber = RomanNumber.valueOf(split[1].trim());
            intergalacticUnitToRomanNumbersMap.addIntergalacticUnitToRomanNumber(intergalacticUnit, romanNumber);
            return true;
        }
        return false;
    }


}
