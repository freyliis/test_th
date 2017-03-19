package com.thoughtworks.test.question;

import com.thoughtworks.test.number.IntergalacticUnitToRomanNumbersMap;
import com.thoughtworks.test.number.RomanNumber;
import com.thoughtworks.test.number.RomanNumberCalculator;
import com.thoughtworks.test.parser.ParserException;
import com.thoughtworks.test.resources.Resource;
import com.thoughtworks.test.resources.ResourcesRepository;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.thoughtworks.test.parser.ReadParser.CREDITS;
import static com.thoughtworks.test.parser.ReadParser.IS_REGEX;
import static com.thoughtworks.test.parser.ReadParser.SEPARATOR;

public class HowManyQuestionProcessor implements QuestionProcessor {

    private RomanNumberCalculator romanNumberCalculator;
    private IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap;
    private ResourcesRepository resourcesRepository;
    private DecimalFormat format = new DecimalFormat("0.#");

    public HowManyQuestionProcessor(RomanNumberCalculator romanNumberCalculator, IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap, ResourcesRepository resourcesRepository) {

        this.romanNumberCalculator = romanNumberCalculator;
        this.intergalacticUnitToRomanNumbersMap = intergalacticUnitToRomanNumbersMap;
        this.resourcesRepository = resourcesRepository;
    }

    @Override
    public String answerQuestion(String question) throws ParserException {
        String[] numbersAndResourceName = question.split(SEPARATOR);
        List<RomanNumber> romanNumbers = new ArrayList<>();
        int indexOfResourceName = parseRomanNumbers(romanNumbers, numbersAndResourceName);
        String resourceName = parseResourceName(numbersAndResourceName, indexOfResourceName);
        Optional<Resource> resourceByName = resourcesRepository.getResourceByName(resourceName);
        if (!resourceByName.isPresent()) {
            throw new ParserException();
        }
        int multiply = romanNumberCalculator.calculate(romanNumbers);
        return getAnswer(question, format.format(multiply * resourceByName.get().getPrice()));
    }

    private String getAnswer(String question, String result) {
        return question + IS_REGEX + result + SEPARATOR + CREDITS;
    }

    private int parseRomanNumbers(List<RomanNumber> romanNumbersInSequence, String[] numbersAndResourceName) {
        int i = 0;
        for (; i < numbersAndResourceName.length; i++) {
            String text = numbersAndResourceName[i];
            Optional<RomanNumber> romanNumberForIntergalacticUnit = intergalacticUnitToRomanNumbersMap.getRomanNumberForIntergalacticUnit(text);
            if (romanNumberForIntergalacticUnit.isPresent()) {
                romanNumbersInSequence.add(romanNumberForIntergalacticUnit.get());
            } else {
                break;
            }
        }
        return i;
    }

    private String parseResourceName(String[] numbersAndResourceName, int indexOfResourceName) {
        return String.join(SEPARATOR, Arrays.copyOfRange(numbersAndResourceName, indexOfResourceName, numbersAndResourceName.length));
    }

}
