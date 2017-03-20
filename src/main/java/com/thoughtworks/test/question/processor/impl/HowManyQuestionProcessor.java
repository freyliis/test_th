package com.thoughtworks.test.question.processor.impl;

import com.thoughtworks.test.definition.DefinitionDictionary;
import com.thoughtworks.test.definition.intergalacticunit.IntergalacticUnit;
import com.thoughtworks.test.definition.resource.Resource;
import com.thoughtworks.test.question.processor.QuestionProcessor;
import com.thoughtworks.test.romannumber.RomanNumberCalculator;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.thoughtworks.test.configuration.impl.DefaultConfiguration.*;

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
    public String answerQuestion(String question) {
        String[] numbersAndResourceName = question.split(SEPARATOR);
        List<IntergalacticUnit> intergalacticUnits = intergalacticUnitDictionary.parseInput(numbersAndResourceName);
        String resourceName = parseResourceName(numbersAndResourceName, intergalacticUnits.size());
        Optional<Resource> resourceByName = resourcesRepository.getDefinitionByKey(resourceName);
        if (!resourceByName.isPresent()) {
            return MESSAGE;
        }
        int multiply = romanNumberCalculator.calculate(intergalacticUnits.stream().map(IntergalacticUnit::getRomanNumber).collect(Collectors.toList()));
        return formatAnswer(question, format.format(multiply * resourceByName.get().getPrice()));
    }

    private String formatAnswer(String question, String result) {
        return question + IS + result + SEPARATOR + CREDITS;
    }

    private String parseResourceName(String[] numbersAndResourceName, int indexOfResourceName) {
        return String.join(SEPARATOR, Arrays.copyOfRange(numbersAndResourceName, indexOfResourceName, numbersAndResourceName.length));
    }


}
