package com.thoughtworks.test.definition.intergalacticunit;


import com.thoughtworks.test.definition.DefinitionDictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IntergalacticUnitDictionary implements DefinitionDictionary<IntergalacticUnit> {

    private List<IntergalacticUnit> intergalacticUnits = new ArrayList<>();

    public void addDefinition(IntergalacticUnit intergalacticUnit) {
        intergalacticUnits.add(intergalacticUnit);
    }

    public Optional<IntergalacticUnit> getDefinitionByKey(String intergalacticUnitName) {
        return intergalacticUnits.stream().filter(intergalacticUnit -> intergalacticUnitName.equals(intergalacticUnit.getUnitName())).findFirst();
    }
}
