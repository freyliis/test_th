package com.thoughtworks.test.question;

import com.thoughtworks.test.number.DefaultRomanNumberCalculator;
import com.thoughtworks.test.number.IntergalacticUnitToRomanNumbersMap;
import com.thoughtworks.test.number.RomanNumber;
import com.thoughtworks.test.number.RomanNumberCalculator;
import com.thoughtworks.test.parser.ParserException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.thoughtworks.test.parser.ParserException.MESSAGE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HowMuchQuestionProcessorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private RomanNumberCalculator romanNumberCalculator = new DefaultRomanNumberCalculator();
    private IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap = new IntergalacticUnitToRomanNumbersMap();

    @Test
    public void shouldThrowAnExceptionDueToMissingNumbers() throws ParserException {
        String question = "pish tegj glob glob";
        expectedException.expect(ParserException.class);
        expectedException.expectMessage(MESSAGE);
        HowMuchQuestionProcessor objectUnderTest = new HowMuchQuestionProcessor(romanNumberCalculator, intergalacticUnitToRomanNumbersMap);
        objectUnderTest.answerQuestion(question);
    }

    @Test
    public void shouldAnswerAQuestionWithExistingNumbers() throws ParserException {
        intergalacticUnitToRomanNumbersMap.addIntergalacticUnitToRomanNumber("pish", RomanNumber.X);
        intergalacticUnitToRomanNumbersMap.addIntergalacticUnitToRomanNumber("tegj", RomanNumber.L);
        intergalacticUnitToRomanNumbersMap.addIntergalacticUnitToRomanNumber("glob", RomanNumber.I);
        String question = "pish tegj glob glob";
        HowMuchQuestionProcessor objectUnderTest = new HowMuchQuestionProcessor(romanNumberCalculator, intergalacticUnitToRomanNumbersMap);
        String result = objectUnderTest.answerQuestion(question);
        assertThat(result, is("pish tegj glob glob is 42"));
    }
}