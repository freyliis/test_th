package com.thoughtworks.test.definition.intergalacticunit;


import com.thoughtworks.test.definition.DefinitionDictionary;

import java.util.*;

public class IntergalacticUnitDictionary implements DefinitionDictionary<IntergalacticUnit> {

    private Set<IntergalacticUnit> intergalacticUnits = new HashSet<>();

    public void addDefinition(IntergalacticUnit intergalacticUnit) {
        checkIfNameIsUnique(intergalacticUnit.getUnitName());
        intergalacticUnits.add(intergalacticUnit);
    }

    private void checkIfNameIsUnique(String unitName) {
        Optional<IntergalacticUnit> first = intergalacticUnits.stream().filter(intergalacticUnit -> intergalacticUnit.getUnitName().equals(unitName)).findFirst();
        if(first.isPresent()) {
            throw new IllegalArgumentException("Intergalactic name already exists: " + unitName);
        }
    }

    public Optional<IntergalacticUnit> getDefinitionByKey(String intergalacticUnitName) {
        return intergalacticUnits.stream().filter(intergalacticUnit -> intergalacticUnitName.equals(intergalacticUnit.getUnitName())).findFirst();
    }

    public List<IntergalacticUnit> parseInput(String[] input) {
        List<IntergalacticUnit> romanNumbersInSequence = new ArrayList<>();
        for (String possibleNumber : input) {
            Optional<IntergalacticUnit> intergalacticUnit = this.getDefinitionByKey(possibleNumber);
            if (intergalacticUnit.isPresent()) {
                romanNumbersInSequence.add(intergalacticUnit.get());
            } else {
                break;
            }
        }
        return romanNumbersInSequence;
    }
}
