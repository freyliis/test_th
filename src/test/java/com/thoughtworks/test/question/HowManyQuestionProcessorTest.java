package com.thoughtworks.test.question;

import com.thoughtworks.test.number.DefaultRomanNumberCalculator;
import com.thoughtworks.test.number.IntergalacticUnitToRomanNumbersMap;
import com.thoughtworks.test.number.RomanNumber;
import com.thoughtworks.test.number.RomanNumberCalculator;
import com.thoughtworks.test.parser.ParserException;
import com.thoughtworks.test.resources.Resource;
import com.thoughtworks.test.resources.ResourcesInMemory;
import com.thoughtworks.test.resources.ResourcesRepository;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class HowManyQuestionProcessorTest {


    private RomanNumberCalculator romanNumberCalculator = new DefaultRomanNumberCalculator();
    private IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap = new IntergalacticUnitToRomanNumbersMap();
    private ResourcesRepository resourcesRepository = new ResourcesInMemory();

    @Test(expected = ParserException.class)
    public void shouldThrowAnExceptionDueToMissingNumbers() throws ParserException {
        String question = "glob prok Silver";
        HowManyQuestionProcessor objectUnderTest = new HowManyQuestionProcessor(romanNumberCalculator, intergalacticUnitToRomanNumbersMap, resourcesRepository);
        objectUnderTest.answerQuestion(question);
    }

    @Test
    public void shouldAnswerAQuestionWithExistingNumbers() throws ParserException {
        intergalacticUnitToRomanNumbersMap.addIntergalacticUnitToRomanNumber("prok", RomanNumber.V);
        intergalacticUnitToRomanNumbersMap.addIntergalacticUnitToRomanNumber("glob", RomanNumber.I);
        resourcesRepository.addResource(new Resource("Silver", 17d));
        String question = "glob prok Silver";
        HowManyQuestionProcessor objectUnderTest = new HowManyQuestionProcessor(romanNumberCalculator, intergalacticUnitToRomanNumbersMap, resourcesRepository);
        String result = objectUnderTest.answerQuestion(question);
        assertThat(result, is("glob prok Silver is 68 Credits"));
    }

}