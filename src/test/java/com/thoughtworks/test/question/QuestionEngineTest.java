package com.thoughtworks.test.question;

import com.thoughtworks.test.definition.intergalacticunit.IntergalacticUnit;
import com.thoughtworks.test.romannumber.DefaultRomanNumberCalculator;
import com.thoughtworks.test.definition.intergalacticunit.IntergalacticUnitDictionary;
import com.thoughtworks.test.romannumber.RomanNumber;
import com.thoughtworks.test.parser.ParserException;
import com.thoughtworks.test.question.processor.HowMuchQuestionProcessor;
import com.thoughtworks.test.question.processor.QuestionProcessor;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.thoughtworks.test.configuration.DefaultConfiguration.HOW_MUCH_IS_REGEX;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class QuestionEngineTest {

    @Test
    public void shouldProcessEmptyQuestionMap() throws ParserException {
        Map<String, QuestionProcessor> processors = new HashMap<>();
        QuestionEngine objectUnderTest = new QuestionEngine(processors);
        List<String> result = objectUnderTest.processQuestions(new QuestionMap());
        assertThat(result.size(), is(0));
    }

    @Test
    public void shouldProcessQuestionMapButEmptyProcessorsMap() throws ParserException {
        Map<String, QuestionProcessor> processors = new HashMap<>();
        QuestionEngine objectUnderTest = new QuestionEngine(processors);
        QuestionMap questionMap = new QuestionMap();
        questionMap.addQuestion(HOW_MUCH_IS_REGEX, "pish tegj glob glob");
        List<String> result = objectUnderTest.processQuestions(questionMap);
        assertThat(result.size(), is(1));
        assertThat(result.get(0), is("I have no idea what you are talking about"));
    }

    @Test
    public void shouldProcessQuestionMapAndReturnDefaultAnswerDueToEmptyNumbers() throws ParserException {
        Map<String, QuestionProcessor> processors = new HashMap<>();
        processors.put(HOW_MUCH_IS_REGEX, new HowMuchQuestionProcessor(new DefaultRomanNumberCalculator(), new IntergalacticUnitDictionary()));
        QuestionEngine objectUnderTest = new QuestionEngine(processors);
        QuestionMap questionMap = new QuestionMap();
        questionMap.addQuestion(HOW_MUCH_IS_REGEX, "pish tegj glob glob");
        List<String> result = objectUnderTest.processQuestions(questionMap);
        assertThat(result.size(), is(1));
        assertThat(result.get(0), is("I have no idea what you are talking about"));
    }

    @Test
    public void shouldProcessQuestionMapAndReturnAnswer() throws ParserException {
        IntergalacticUnitDictionary intergalacticUnitDictionary = new IntergalacticUnitDictionary();
        intergalacticUnitDictionary.addDefinition(new IntergalacticUnit("pish", RomanNumber.X));
        intergalacticUnitDictionary.addDefinition(new IntergalacticUnit("tegj", RomanNumber.L));
        intergalacticUnitDictionary.addDefinition(new IntergalacticUnit("glob", RomanNumber.I));
        Map<String, QuestionProcessor> processors = new HashMap<>();
        processors.put(HOW_MUCH_IS_REGEX, new HowMuchQuestionProcessor(new DefaultRomanNumberCalculator(), intergalacticUnitDictionary));
        QuestionEngine objectUnderTest = new QuestionEngine(processors);
        QuestionMap questionMap = new QuestionMap();
        questionMap.addQuestion(HOW_MUCH_IS_REGEX, "pish tegj glob glob");
        List<String> result = objectUnderTest.processQuestions(questionMap);
        assertThat(result.size(), is(1));
        assertThat(result.get(0), is("pish tegj glob glob is 42"));
    }
}