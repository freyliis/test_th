package com.thoughtworks.test.question;

import com.thoughtworks.test.number.DefaultRomanNumberCalculator;
import com.thoughtworks.test.number.IntergalacticUnitToRomanNumbersMap;
import com.thoughtworks.test.number.RomanNumber;
import com.thoughtworks.test.number.RomanNumberCalculator;
import com.thoughtworks.test.parser.ParserException;
import com.thoughtworks.test.resources.Resource;
import com.thoughtworks.test.resources.ResourcesInMemory;
import com.thoughtworks.test.resources.ResourcesRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.thoughtworks.test.parser.ParserException.MESSAGE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HowManyQuestionProcessorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private RomanNumberCalculator romanNumberCalculator = new DefaultRomanNumberCalculator();
    private IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap = new IntergalacticUnitToRomanNumbersMap();
    private ResourcesRepository resourcesRepository = new ResourcesInMemory();

    @Test
    public void shouldThrowAnExceptionDueToMissingNumbers() throws ParserException {
        String question = "glob prok Silver";
        expectedException.expect(ParserException.class);
        expectedException.expectMessage(MESSAGE);
        HowManyQuestionProcessor objectUnderTest = new HowManyQuestionProcessor(romanNumberCalculator, intergalacticUnitToRomanNumbersMap, resourcesRepository);
        objectUnderTest.answerQuestion(question);
    }

    @Test
    public void shouldThrowAnExceptionDueToMissingResource() throws ParserException {
        intergalacticUnitToRomanNumbersMap.addIntergalacticUnitToRomanNumber("prok", RomanNumber.V);
        intergalacticUnitToRomanNumbersMap.addIntergalacticUnitToRomanNumber("glob", RomanNumber.I);
        String question = "glob prok Silver";
        expectedException.expect(ParserException.class);
        expectedException.expectMessage(MESSAGE);
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