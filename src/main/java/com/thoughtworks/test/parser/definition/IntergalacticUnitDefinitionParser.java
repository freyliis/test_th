package com.thoughtworks.test.parser.definition;

import com.thoughtworks.test.definition.intergalacticunit.IntergalacticUnit;
import com.thoughtworks.test.definition.intergalacticunit.IntergalacticUnitDictionary;
import com.thoughtworks.test.parser.ParserException;
import com.thoughtworks.test.parser.ReadParser;
import com.thoughtworks.test.romannumber.RomanNumber;

import static com.thoughtworks.test.configuration.impl.DefaultConfiguration.IS;
import static com.thoughtworks.test.configuration.impl.DefaultConfiguration.NUMBER_DEFINITION_REGEX;

public class IntergalacticUnitDefinitionParser implements ReadParser {

    private IntergalacticUnitDictionary intergalacticUnitDictionary;

    public IntergalacticUnitDefinitionParser(IntergalacticUnitDictionary intergalacticUnitDictionary) {
        this.intergalacticUnitDictionary = intergalacticUnitDictionary;
    }

    public boolean parse(String inputText) throws ParserException {
        if (inputText.matches(NUMBER_DEFINITION_REGEX)) {
            String[] split = inputText.split(IS);
            String intergalacticUnit = split[0].trim();
            try {
                RomanNumber romanNumber = RomanNumber.valueOf(split[1].trim());
                intergalacticUnitDictionary.addDefinition(new IntergalacticUnit(intergalacticUnit, romanNumber));
            } catch (IllegalArgumentException e) {
                throw new ParserException(e.getMessage());
            }
            return true;
        }
        return false;
    }


}
