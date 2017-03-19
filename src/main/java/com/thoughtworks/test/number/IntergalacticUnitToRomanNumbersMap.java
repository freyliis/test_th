package com.thoughtworks.test.number;


import java.util.*;

public class IntergalacticUnitToRomanNumbersMap {

    private Map<String, RomanNumber> intergalacticUnitsToRomanNumbers = new HashMap<>();

    public void addIntergalacticUnitToRomanNumber(String text, RomanNumber romanNumber) {
        intergalacticUnitsToRomanNumbers.put(text, romanNumber);
    }

    public Optional<RomanNumber> getRomanNumberForIntergalacticUnit(String intergalacticUnit) {
        return Optional.ofNullable(intergalacticUnitsToRomanNumbers.get(intergalacticUnit));
    }

    public List<RomanNumber> readRomanNumbers(String[] numbersAndResourceName) {
        List<RomanNumber> romanNumbersInSequence = new ArrayList<>();
        for (String possibleNumber : numbersAndResourceName) {
            Optional<RomanNumber> romanNumberForIntergalacticUnit = this.getRomanNumberForIntergalacticUnit(possibleNumber);
            if (romanNumberForIntergalacticUnit.isPresent()) {
                romanNumbersInSequence.add(romanNumberForIntergalacticUnit.get());
            } else {
                break;
            }
        }
        return romanNumbersInSequence;
    }

}
