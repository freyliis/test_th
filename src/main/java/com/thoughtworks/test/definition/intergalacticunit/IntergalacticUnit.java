package com.thoughtworks.test.definition.intergalacticunit;

import com.thoughtworks.test.definition.Definition;
import com.thoughtworks.test.definition.number.RomanNumber;

public class IntergalacticUnit implements Definition {

    private final String unitName;
    private final RomanNumber romanNumber;

    public IntergalacticUnit(String unitName, RomanNumber romanNumber) {
        this.unitName = unitName;
        this.romanNumber = romanNumber;
    }

    public String getUnitName() {
        return unitName;
    }

    public RomanNumber getRomanNumber() {
        return romanNumber;
    }
}
