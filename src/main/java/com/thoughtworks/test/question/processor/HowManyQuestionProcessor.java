package com.thoughtworks.test.question.processor;

import com.thoughtworks.test.definition.DefinitionDictionary;
import com.thoughtworks.test.definition.intergalacticunit.IntergalacticUnit;
import com.thoughtworks.test.definition.number.RomanNumber;
import com.thoughtworks.test.definition.number.RomanNumberCalculator;
import com.thoughtworks.test.definition.resource.Resource;
import com.thoughtworks.test.parser.ParserException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.thoughtworks.test.configuration.DefaultConfiguration.*;

public class HowManyQuestionProcessor implements QuestionProcessor {

    private RomanNumberCalculator romanNumberCalculator;
    private DefinitionDictionary<IntergalacticUnit> intergalacticUnitDictionary;
    private DefinitionDictionary<Resource> resourcesRepository;
    private DecimalFormat format = new DecimalFormat("0.#");

    public HowManyQuestionProcessor(RomanNumberCalculator romanNumberCalculator,
                                    DefinitionDictionary<IntergalacticUnit> intergalacticUnitDictionary, DefinitionDictionary<Resource> resourcesRepository) {
        this.romanNumberCalculator = romanNumberCalculator;
        this.intergalacticUnitDictionary = intergalacticUnitDictionary;
        this.resourcesRepository = resourcesRepository;
    }

    @Override
    public String answerQuestion(String question) throws ParserException {
        String[] numbersAndResourceName = question.split(SEPARATOR);
        List<RomanNumber> romanNumbers = this.readRomanNumbers(intergalacticUnitDictionary, numbersAndResourceName);
        String resourceName = parseResourceName(numbersAndResourceName, romanNumbers.size());
        Optional<Resource> resourceByName = resourcesRepository.getDefinitionByKey(resourceName);
        if (!resourceByName.isPresent()) {
            throw new ParserException();
        }
        int multiply = romanNumberCalculator.calculate(romanNumbers);
        return formatAnswer(question, format.format(multiply * resourceByName.get().getPrice()));
    }

    private String formatAnswer(String question, String result) {
        return question + IS + result + SEPARATOR + CREDITS;
    }

    private String parseResourceName(String[] numbersAndResourceName, int indexOfResourceName) {
        return String.join(SEPARATOR, Arrays.copyOfRange(numbersAndResourceName, indexOfResourceName, numbersAndResourceName.length));
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
