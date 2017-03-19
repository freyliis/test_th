package com.thoughtworks.test.question;

import com.thoughtworks.test.number.DefaultRomanNumberCalculator;
import com.thoughtworks.test.number.IntergalacticUnitToRomanNumbersMap;
import com.thoughtworks.test.number.RomanNumber;
import com.thoughtworks.test.number.RomanNumberCalculator;
import com.thoughtworks.test.parser.ParserException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class HowMuchQuestionProcessorTest {

    private RomanNumberCalculator romanNumberCalculator = new DefaultRomanNumberCalculator();
    private IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap = new IntergalacticUnitToRomanNumbersMap();

    @Test(expected = ParserException.class)
    public void shouldThrowAnExceptionDueToMissingNumbers() throws ParserException {
        String question = "pish tegj glob glob";
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