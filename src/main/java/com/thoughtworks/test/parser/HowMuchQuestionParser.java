package com.thoughtworks.test.parser;

import com.thoughtworks.test.IntergalacticUnitToRomanNumbersMap;
import com.thoughtworks.test.RomanNumber;
import com.thoughtworks.test.RomanNumberCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HowMuchQuestionParser implements ReadParser {

    private final RomanNumberCalculator romanNumberCalculator;
    private final IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap;
    private final Map<String, String> results;

    public HowMuchQuestionParser(RomanNumberCalculator romanNumberCalculator, IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap, Map<String, String> results) {
        this.romanNumberCalculator = romanNumberCalculator;
        this.intergalacticUnitToRomanNumbersMap = intergalacticUnitToRomanNumbersMap;
        this.results = results;
    }

    public boolean parse(String inputText) throws ParserException {
        if (inputText.startsWith(HOW_MUCH_IS_REGEX)) {
            String questionValues = inputText.replace(HOW_MUCH_IS_REGEX, "").replace("?", "").trim();
            String[] split = questionValues.split(SEPARATOR);
            List<RomanNumber> romanNumbers = new ArrayList<>();
            for (String text : split) {
                Optional<RomanNumber> romanNumberForIntergalacticUnit = intergalacticUnitToRomanNumbersMap.getRomanNumberForIntergalacticUnit(text);
                if (romanNumberForIntergalacticUnit.isPresent()) {
                    romanNumbers.add(romanNumberForIntergalacticUnit.get());
                } else {
                    throw new ParserException();
                }
            }
            int result = romanNumberCalculator.calculate(romanNumbers);
            results.put(questionValues, Integer.toString(result));
            return true;
        }
        return false;
    }
}
