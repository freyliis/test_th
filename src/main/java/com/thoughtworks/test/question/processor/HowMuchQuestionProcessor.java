package com.thoughtworks.test.question.processor;

import com.thoughtworks.test.definition.DefinitionDictionary;
import com.thoughtworks.test.definition.intergalacticunit.IntergalacticUnit;
import com.thoughtworks.test.definition.number.RomanNumber;
import com.thoughtworks.test.definition.number.RomanNumberCalculator;
import com.thoughtworks.test.parser.ParserException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.thoughtworks.test.configuration.DefaultConfiguration.IS;
import static com.thoughtworks.test.configuration.DefaultConfiguration.SEPARATOR;

public class HowMuchQuestionProcessor implements QuestionProcessor {

    private final RomanNumberCalculator romanNumberCalculator;
    private final DefinitionDictionary<IntergalacticUnit> intergalacticUnitDictionary;

    public HowMuchQuestionProcessor(RomanNumberCalculator romanNumberCalculator, DefinitionDictionary<IntergalacticUnit> intergalacticUnitDictionary) {
        this.romanNumberCalculator = romanNumberCalculator;
        this.intergalacticUnitDictionary = intergalacticUnitDictionary;
    }

    @Override
    public String answerQuestion(String question) throws ParserException {
        String[] numbers = question.split(SEPARATOR);
        List<RomanNumber> romanNumbers = this.readRomanNumbers(intergalacticUnitDictionary, numbers);
        if (romanNumbers.size() != numbers.length) {
            throw new ParserException();
        }
        int result = romanNumberCalculator.calculate(romanNumbers);
        return getAnswer(question, Integer.toString(result));
    }

    private String getAnswer(String question, String result) {
        return question + IS + result;
    }

    public List<RomanNumber> readRomanNumbers(DefinitionDictionary<IntergalacticUnit> romanNumberDefinitionDictionary, String[] numbersAndResourceName) {
        List<RomanNumber> romanNumbersInSequence = new ArrayList<>();
        for (String possibleNumber : numbersAndResourceName) {
            Optional<IntergalacticUnit> intergalacticUnit = romanNumberDefinitionDictionary.getDefinitionByKey(possibleNumber);
            if (intergalacticUnit.isPresent()) {
                romanNumbersInSequence.add(intergalacticUnit.get().getRomanNumber());
            } else {
                break;
            }
        }
        return romanNumbersInSequence;
    }
}
