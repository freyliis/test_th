package com.thoughtworks.test.question;

import com.thoughtworks.test.number.IntergalacticUnitToRomanNumbersMap;
import com.thoughtworks.test.number.RomanNumber;
import com.thoughtworks.test.number.RomanNumberCalculator;
import com.thoughtworks.test.parser.ParserException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.thoughtworks.test.parser.ReadParser.IS_REGEX;
import static com.thoughtworks.test.parser.ReadParser.SEPARATOR;

public class HowMuchQuestionProcessor implements QuestionProcessor {

    private final RomanNumberCalculator romanNumberCalculator;
    private final IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap;

    public HowMuchQuestionProcessor(RomanNumberCalculator romanNumberCalculator, IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap) {
        this.romanNumberCalculator = romanNumberCalculator;
        this.intergalacticUnitToRomanNumbersMap = intergalacticUnitToRomanNumbersMap;
    }

    @Override
    public String answerQuestion(String question) throws ParserException {
        String[] split = question.split(SEPARATOR);
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
        return getAnswer(question, Integer.toString(result));
    }

    private String getAnswer(String question, String result) {
        return question + IS_REGEX + result;
    }
}
