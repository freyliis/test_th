package com.thoughtworks.test.question.processor.impl;

import com.thoughtworks.test.definition.DefinitionDictionary;
import com.thoughtworks.test.definition.intergalacticunit.IntergalacticUnit;
import com.thoughtworks.test.definition.intergalacticunit.IntergalacticUnitDictionary;
import com.thoughtworks.test.parser.ParserException;
import com.thoughtworks.test.question.processor.impl.HowMuchQuestionProcessor;
import com.thoughtworks.test.romannumber.DefaultRomanNumberCalculator;
import com.thoughtworks.test.romannumber.RomanNumber;
import com.thoughtworks.test.romannumber.RomanNumberCalculator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.thoughtworks.test.configuration.impl.DefaultConfiguration.MESSAGE;
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
        HowMuchQuestionProcessor objectUnderTest = new HowMuchQuestionProcessor(romanNumberCalculator, intergalacticUnitDictionary);
        String result = objectUnderTest.answerQuestion(question);
        assertThat(result, is(MESSAGE));

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