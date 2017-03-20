package com.thoughtworks.test.definition.intergalacticunit;

import com.thoughtworks.test.definition.Definition;
import com.thoughtworks.test.romannumber.RomanNumber;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntergalacticUnit that = (IntergalacticUnit) o;

        return romanNumber == that.romanNumber;
    }

    @Override
    public int hashCode() {
        return romanNumber != null ? romanNumber.hashCode() : 0;
    }
}
