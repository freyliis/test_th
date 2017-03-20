package com.thoughtworks.test.parser.definition;

import com.thoughtworks.test.definition.DefinitionDictionary;
import com.thoughtworks.test.definition.intergalacticunit.IntergalacticUnit;
import com.thoughtworks.test.definition.number.RomanNumber;
import com.thoughtworks.test.definition.number.RomanNumberCalculator;
import com.thoughtworks.test.definition.resource.Resource;
import com.thoughtworks.test.parser.ReadParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.thoughtworks.test.configuration.DefaultConfiguration.*;

public class ResourceParser implements ReadParser {

    private DefinitionDictionary intergalacticUnitDictionary;
    private DefinitionDictionary resourcesRepository;
    private RomanNumberCalculator romanNumberCalculator;

    public ResourceParser(DefinitionDictionary intergalacticUnitDictionary, DefinitionDictionary resourcesRepository, RomanNumberCalculator romanNumberCalculator) {
        this.intergalacticUnitDictionary = intergalacticUnitDictionary;
        this.resourcesRepository = resourcesRepository;
        this.romanNumberCalculator = romanNumberCalculator;
    }

    public boolean parse(String inputText) {
        if (inputText.matches(RESOURCE_DEFINITION_REGEX)) {
            String[] split = inputText.split(IS);
            String[] numbersAndResourceName = split[0].split(SEPARATOR);
            List<RomanNumber> romanNumbersInSequence = readRomanNumbers(intergalacticUnitDictionary, numbersAndResourceName);
            if (romanNumbersInSequence.size() > 0) {
                String resourceName = parseResourceName(numbersAndResourceName, romanNumbersInSequence.size());
                double totalPrice = getTotalPrice(split[1]);
                double resourcePrice = totalPrice / romanNumberCalculator.calculate(romanNumbersInSequence);
                resourcesRepository.addDefinition(new Resource(resourceName, resourcePrice));
                return true;
            } else {
                throw new IllegalArgumentException("There are no proper numbers in the input: " + inputText);
            }
        }
        return false;
    }

    private String parseResourceName(String[] numbersAndResourceName, int indexOfResourceName) {
        return String.join(SEPARATOR, Arrays.copyOfRange(numbersAndResourceName, indexOfResourceName, numbersAndResourceName.length));
    }

    private double getTotalPrice(String inputText) {
        return Double.parseDouble(inputText.replace(CREDITS, "").trim());
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
