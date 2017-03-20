package com.thoughtworks.test.question.processor;

import com.thoughtworks.test.definition.DefinitionDictionary;
import com.thoughtworks.test.definition.intergalacticunit.IntergalacticUnit;
import com.thoughtworks.test.romannumber.RomanNumberCalculator;

import java.util.List;
import java.util.stream.Collectors;

import static com.thoughtworks.test.configuration.DefaultConfiguration.*;

public class HowMuchQuestionProcessor implements QuestionProcessor {

    private final RomanNumberCalculator romanNumberCalculator;
    private final DefinitionDictionary<IntergalacticUnit> intergalacticUnitDictionary;

    public HowMuchQuestionProcessor(RomanNumberCalculator romanNumberCalculator, DefinitionDictionary<IntergalacticUnit> intergalacticUnitDictionary) {
        this.romanNumberCalculator = romanNumberCalculator;
        this.intergalacticUnitDictionary = intergalacticUnitDictionary;
    }

    @Override
    public String answerQuestion(String question) {
        String[] numbers = question.split(SEPARATOR);
        List<IntergalacticUnit> intergalacticUnits = intergalacticUnitDictionary.parseInput(numbers);
        if (intergalacticUnits.size() != numbers.length) {
            return MESSAGE;
        }
        int result = romanNumberCalculator.calculate(intergalacticUnits.stream().map(IntergalacticUnit::getRomanNumber).collect(Collectors.toList()));
        return getAnswer(question, Integer.toString(result));
    }

    private String getAnswer(String question, String result) {
        return question + IS + result;
    }

}
