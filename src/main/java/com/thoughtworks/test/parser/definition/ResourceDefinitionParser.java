package com.thoughtworks.test.parser.definition;

import com.thoughtworks.test.definition.DefinitionDictionary;
import com.thoughtworks.test.definition.intergalacticunit.IntergalacticUnit;
import com.thoughtworks.test.definition.resource.Resource;
import com.thoughtworks.test.parser.ParserException;
import com.thoughtworks.test.parser.ReadParser;
import com.thoughtworks.test.romannumber.RomanNumberCalculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.thoughtworks.test.configuration.DefaultConfiguration.*;

public class ResourceDefinitionParser implements ReadParser {

    private DefinitionDictionary<IntergalacticUnit> intergalacticUnitDictionary;
    private DefinitionDictionary<Resource> resourcesRepository;
    private RomanNumberCalculator romanNumberCalculator;

    public ResourceDefinitionParser(DefinitionDictionary<IntergalacticUnit> intergalacticUnitDictionary,
                                    DefinitionDictionary<Resource> resourcesRepository, RomanNumberCalculator romanNumberCalculator) {
        this.intergalacticUnitDictionary = intergalacticUnitDictionary;
        this.resourcesRepository = resourcesRepository;
        this.romanNumberCalculator = romanNumberCalculator;
    }

    public boolean parse(String inputText) throws ParserException {
        if (inputText.matches(RESOURCE_DEFINITION_REGEX)) {
            String[] split = inputText.split(IS);
            String[] numbersAndResourceName = split[0].split(SEPARATOR);
            List<IntergalacticUnit> intergalacticUnits = intergalacticUnitDictionary.parseInput(numbersAndResourceName);
            if (intergalacticUnits.size() > 0) {
                String resourceName = parseResourceName(numbersAndResourceName, intergalacticUnits.size());
                double totalPrice = getTotalPrice(split[1]);
                double resourcePrice = totalPrice / romanNumberCalculator.calculate(intergalacticUnits.stream().map(IntergalacticUnit::getRomanNumber).collect(Collectors.toList()));
                resourcesRepository.addDefinition(new Resource(resourceName, resourcePrice));
                return true;
            } else {
                throw new ParserException("There are no proper numbers in the input: " + inputText);
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

}
