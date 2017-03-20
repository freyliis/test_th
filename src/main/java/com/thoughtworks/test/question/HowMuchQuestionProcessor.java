package com.thoughtworks.test.question;

import com.thoughtworks.test.number.IntergalacticUnitToRomanNumbersMap;
import com.thoughtworks.test.number.RomanNumber;
import com.thoughtworks.test.number.RomanNumberCalculator;
import com.thoughtworks.test.parser.ParserException;

import java.util.List;

import static com.thoughtworks.test.configuration.DefaultConfiguration.IS;
import static com.thoughtworks.test.configuration.DefaultConfiguration.SEPARATOR;

public class HowMuchQuestionProcessor implements QuestionProcessor {

    private final RomanNumberCalculator romanNumberCalculator;
    private final IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap;

    public HowMuchQuestionProcessor(RomanNumberCalculator romanNumberCalculator, IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap) {
        this.romanNumberCalculator = romanNumberCalculator;
        this.intergalacticUnitToRomanNumbersMap = intergalacticUnitToRomanNumbersMap;
    }

    @Override
    public String answerQuestion(String question) throws ParserException {
        String[] numbers = question.split(SEPARATOR);
        List<RomanNumber> romanNumbers = intergalacticUnitToRomanNumbersMap.readRomanNumbers(numbers);
        if (romanNumbers.size() != numbers.length) {
            throw new ParserException();
        }
        int result = romanNumberCalculator.calculate(romanNumbers);
        return getAnswer(question, Integer.toString(result));
    }

    private String getAnswer(String question, String result) {
        return question + IS + result;
    }
}
