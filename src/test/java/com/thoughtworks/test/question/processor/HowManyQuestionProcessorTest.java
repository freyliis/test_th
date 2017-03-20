package com.thoughtworks.test.question.processor;

import com.thoughtworks.test.definition.DefinitionDictionary;
import com.thoughtworks.test.definition.intergalacticunit.IntergalacticUnit;
import com.thoughtworks.test.definition.intergalacticunit.IntergalacticUnitDictionary;
import com.thoughtworks.test.definition.resource.Resource;
import com.thoughtworks.test.definition.resource.ResourcesDictionary;
import com.thoughtworks.test.parser.ParserException;
import com.thoughtworks.test.romannumber.DefaultRomanNumberCalculator;
import com.thoughtworks.test.romannumber.RomanNumber;
import com.thoughtworks.test.romannumber.RomanNumberCalculator;
import org.junit.Test;

import static com.thoughtworks.test.configuration.DefaultConfiguration.MESSAGE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HowManyQuestionProcessorTest {

    private RomanNumberCalculator romanNumberCalculator = new DefaultRomanNumberCalculator();
    private DefinitionDictionary<IntergalacticUnit> intergalacticUnitDictionary = new IntergalacticUnitDictionary();
    private DefinitionDictionary<Resource> resourcesRepository = new ResourcesDictionary();

    @Test
    public void shouldReturnDefaultMessageDueToMissingNumbers() throws ParserException {
        String question = "glob prok Silver";
        HowManyQuestionProcessor objectUnderTest = new HowManyQuestionProcessor(romanNumberCalculator, intergalacticUnitDictionary, resourcesRepository);
        String result = objectUnderTest.answerQuestion(question);
        assertThat(result, is(MESSAGE));
    }

    @Test
    public void shouldReturnDefaultMessageToMissingResource() {
        intergalacticUnitDictionary.addDefinition(new IntergalacticUnit("prok", RomanNumber.V));
        intergalacticUnitDictionary.addDefinition(new IntergalacticUnit("glob", RomanNumber.I));
        String question = "glob prok Silver";
        HowManyQuestionProcessor objectUnderTest = new HowManyQuestionProcessor(romanNumberCalculator, intergalacticUnitDictionary, resourcesRepository);
        String result = objectUnderTest.answerQuestion(question);
        assertThat(result, is(MESSAGE));
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