package com.thoughtworks.test.question;

import com.thoughtworks.test.definition.DefinitionDictionary;
import com.thoughtworks.test.definition.intergalacticunit.IntergalacticUnit;
import com.thoughtworks.test.definition.number.DefaultRomanNumberCalculator;
import com.thoughtworks.test.definition.intergalacticunit.IntergalacticUnitDictionary;
import com.thoughtworks.test.definition.number.RomanNumber;
import com.thoughtworks.test.definition.number.RomanNumberCalculator;
import com.thoughtworks.test.parser.ParserException;
import com.thoughtworks.test.question.processor.HowManyQuestionProcessor;
import com.thoughtworks.test.definition.resource.Resource;
import com.thoughtworks.test.definition.resource.ResourcesDictionary;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HowManyQuestionProcessorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private RomanNumberCalculator romanNumberCalculator = new DefaultRomanNumberCalculator();
    private DefinitionDictionary<IntergalacticUnit> intergalacticUnitDictionary = new IntergalacticUnitDictionary();
    private DefinitionDictionary<Resource> resourcesRepository = new ResourcesDictionary();

    @Test
    public void shouldThrowAnExceptionDueToMissingNumbers() throws ParserException {
        String question = "glob prok Silver";
        expectedException.expect(ParserException.class);
        HowManyQuestionProcessor objectUnderTest = new HowManyQuestionProcessor(romanNumberCalculator, intergalacticUnitDictionary, resourcesRepository);
        objectUnderTest.answerQuestion(question);
    }

    @Test
    public void shouldThrowAnExceptionDueToMissingResource() throws ParserException {
        intergalacticUnitDictionary.addDefinition(new IntergalacticUnit("prok", RomanNumber.V));
        intergalacticUnitDictionary.addDefinition(new IntergalacticUnit("glob", RomanNumber.I));
        String question = "glob prok Silver";
        expectedException.expect(ParserException.class);
        HowManyQuestionProcessor objectUnderTest = new HowManyQuestionProcessor(romanNumberCalculator, intergalacticUnitDictionary, resourcesRepository);
        objectUnderTest.answerQuestion(question);
    }

    @Test
    public void shouldAnswerAQuestionWithExistingNumbers() throws ParserException {
        intergalacticUnitDictionary.addDefinition(new IntergalacticUnit("prok", RomanNumber.V));
        intergalacticUnitDictionary.addDefinition(new IntergalacticUnit("glob", RomanNumber.I));
        resourcesRepository.addDefinition(new Resource("Silver", 17d));
        String question = "glob prok Silver";
        HowManyQuestionProcessor objectUnderTest = new HowManyQuestionProcessor(romanNumberCalculator, intergalacticUnitDictionary, resourcesRepository);
        String result = objectUnderTest.answerQuestion(question);
        assertThat(result, is("glob prok Silver is 68 Credits"));
    }
}