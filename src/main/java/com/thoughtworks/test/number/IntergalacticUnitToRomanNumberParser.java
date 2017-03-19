package com.thoughtworks.test.number;

import com.thoughtworks.test.number.IntergalacticUnitToRomanNumbersMap;
import com.thoughtworks.test.number.RomanNumber;
import com.thoughtworks.test.parser.ReadParser;

public class IntergalacticUnitToRomanNumberParser implements ReadParser {


    private IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap;

    public IntergalacticUnitToRomanNumberParser(IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap) {
        this.intergalacticUnitToRomanNumbersMap = intergalacticUnitToRomanNumbersMap;
    }

    public boolean parse(String inputText) {
        String[] split = inputText.split(IS_REGEX);
        if (split.length == 2) {
            String intergalacticUnit = split[0].trim();
            RomanNumber romanNumber = RomanNumber.valueOf(split[1].trim());
            intergalacticUnitToRomanNumbersMap.addIntergalacticUnitToRomanNumber(intergalacticUnit, romanNumber);
            return true;
        }
        return false;
    }
}
