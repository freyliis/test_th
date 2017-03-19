package com.thoughtworks.test.question;

import com.thoughtworks.test.number.DefaultRomanNumberCalculator;
import com.thoughtworks.test.number.IntergalacticUnitToRomanNumbersMap;
import com.thoughtworks.test.number.RomanNumber;
import com.thoughtworks.test.parser.ParserException;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.thoughtworks.test.parser.ReadParser.HOW_MUCH_IS_REGEX;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class QuestionsProcessorTest {

    @Test
    public void shouldProcessEmptyQuestionMap() throws ParserException {
        Map<String, QuestionProcessor> processors = new HashMap<>();
        QuestionsProcessor objectUnderTest = new QuestionsProcessor(processors);
        List<String> result = objectUnderTest.processQuestions(new QuestionMap());
        assertThat(result.size(), is(0));
    }

    @Test
    public void shouldProcessQuestionMapButEmptyProcessorsMap() throws ParserException {
        Map<String, QuestionProcessor> processors = new HashMap<>();
        QuestionsProcessor objectUnderTest = new QuestionsProcessor(processors);
        QuestionMap questionMap = new QuestionMap();
        questionMap.addQuestion(HOW_MUCH_IS_REGEX, "pish tegj glob glob");
        List<String> result = objectUnderTest.processQuestions(questionMap);
        assertThat(result.size(), is(1));
        assertThat(result.get(0), is("I have no idea what you are talking about"));
    }

    @Test
    public void shouldProcessQuestionMapAndReturnDefaultAnswerDueToEmptyNumbers() throws ParserException {
        Map<String, QuestionProcessor> processors = new HashMap<>();
        processors.put(HOW_MUCH_IS_REGEX, new HowMuchQuestionProcessor(new DefaultRomanNumberCalculator(), new IntergalacticUnitToRomanNumbersMap()));
        QuestionsProcessor objectUnderTest = new QuestionsProcessor(processors);
        QuestionMap questionMap = new QuestionMap();
        questionMap.addQuestion(HOW_MUCH_IS_REGEX, "pish tegj glob glob");
        List<String> result = objectUnderTest.processQuestions(questionMap);
        assertThat(result.size(), is(1));
        assertThat(result.get(0), is("I have no idea what you are talking about"));
    }

    @Test
    public void shouldProcessQuestionMapAndReturnAnswer() throws ParserException {
        IntergalacticUnitToRomanNumbersMap intergalacticUnitToRomanNumbersMap = new IntergalacticUnitToRomanNumbersMap();
        intergalacticUnitToRomanNumbersMap.addIntergalacticUnitToRomanNumber("pish", RomanNumber.X);
        intergalacticUnitToRomanNumbersMap.addIntergalacticUnitToRomanNumber("tegj", RomanNumber.L);
        intergalacticUnitToRomanNumbersMap.addIntergalacticUnitToRomanNumber("glob", RomanNumber.I);
        Map<String, QuestionProcessor> processors = new HashMap<>();
        processors.put(HOW_MUCH_IS_REGEX, new HowMuchQuestionProcessor(new DefaultRomanNumberCalculator(), intergalacticUnitToRomanNumbersMap));
        QuestionsProcessor objectUnderTest = new QuestionsProcessor(processors);
        QuestionMap questionMap = new QuestionMap();
        questionMap.addQuestion(HOW_MUCH_IS_REGEX, "pish tegj glob glob");
        List<String> result = objectUnderTest.processQuestions(questionMap);
        assertThat(result.size(), is(1));
        assertThat(result.get(0), is("pish tegj glob glob is 42"));
    }
}