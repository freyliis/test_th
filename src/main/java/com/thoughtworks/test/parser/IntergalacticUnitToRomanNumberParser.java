package com.thoughtworks.test.parser;

import com.thoughtworks.test.IntergalacticUnitToRomanNumbersMap;
import com.thoughtworks.test.RomanNumber;

public class IntergalacticUnitToRomanNumberParser {

    private IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap;

    public IntergalacticUnitToRomanNumberParser(IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap) {
        this.intergalacticUnitToRomanNumbersMap = intergalacticUnitToRomanNumbersMap;
    }

    public void parse(String inputText) {
        String[] split = inputText.split("is");
        String intergalacticUnit = split[0].trim();
        RomanNumber romanNumber = RomanNumber.valueOf(split[1].trim());
        intergalacticUnitToRomanNumbersMap.addIntergalacticUnitToRomanNumber(intergalacticUnit, romanNumber);
    }
}
