package com.thoughtworks.test.question;

import com.thoughtworks.test.definition.DefinitionDictionary;
import com.thoughtworks.test.definition.intergalacticunit.IntergalacticUnit;
import com.thoughtworks.test.definition.number.DefaultRomanNumberCalculator;
import com.thoughtworks.test.definition.intergalacticunit.IntergalacticUnitDictionary;
import com.thoughtworks.test.definition.number.RomanNumber;
import com.thoughtworks.test.definition.number.RomanNumberCalculator;
import com.thoughtworks.test.parser.ParserException;
import com.thoughtworks.test.question.processor.HowMuchQuestionProcessor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HowMuchQuestionProcessorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private RomanNumberCalculator romanNumberCalculator = new DefaultRomanNumberCalculator();
    private DefinitionDictionary<IntergalacticUnit> intergalacticUnitDictionary = new IntergalacticUnitDictionary();

    @Test
    public void shouldThrowAParseExceptionDueToMissingNumbers() throws ParserException {
        String question = "pish tegj glob glob";
        expectedException.expect(ParserException.class);
        HowMuchQuestionProcessor objectUnderTest = new HowMuchQuestionProcessor(romanNumberCalculator, intergalacticUnitDictionary);
        objectUnderTest.answerQuestion(question);
    }

    @Test
    public void shouldAnswerAQuestionWithExistingNumbers() throws ParserException {
        intergalacticUnitDictionary.addDefinition(new IntergalacticUnit("pish", RomanNumber.X));
        intergalacticUnitDictionary.addDefinition(new IntergalacticUnit("tegj", RomanNumber.L));
        intergalacticUnitDictionary.addDefinition(new IntergalacticUnit("glob", RomanNumber.I));
        String question = "pish tegj glob glob";
        HowMuchQuestionProcessor objectUnderTest = new HowMuchQuestionProcessor(romanNumberCalculator, intergalacticUnitDictionary);
        String result = objectUnderTest.answerQuestion(question);
        assertThat(result, is("pish tegj glob glob is 42"));
    }
}