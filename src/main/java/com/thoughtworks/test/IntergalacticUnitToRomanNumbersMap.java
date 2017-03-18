package com.thoughtworks.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class IntergalacticUnitToRomanNumbersMap {

    private Map<String, RomanNumber> intergalacticUnitsToRomanNumbers = new HashMap<>();

    public void addIntergalacticUnitToRomanNumber(String text, RomanNumber romanNumber) {
        intergalacticUnitsToRomanNumbers.put(text, romanNumber);
    }

    public Optional<RomanNumber> getRomanNumberForIntergalacticUnit(String intergalacticUnit) {
        return Optional.ofNullable(intergalacticUnitsToRomanNumbers.get(intergalacticUnit));
    }
}
