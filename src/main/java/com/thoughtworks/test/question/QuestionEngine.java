package com.thoughtworks.test.question;

import com.thoughtworks.test.parser.ParserException;
import com.thoughtworks.test.question.processor.QuestionProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.thoughtworks.test.configuration.DefaultConfiguration.MESSAGE;

public class QuestionEngine {

    private final Map<String, QuestionProcessor> processors = new HashMap<>();

    public QuestionEngine(Map<String, QuestionProcessor> processors) {
        this.processors.putAll(processors);
    }

    public List<String> processQuestions(DefaultQuestionList questionMap) {
        List<String> answers = new ArrayList<>();
        List<Question> questionsForRegex = questionMap.getQuestions();
        for (Question question : questionsForRegex)
            try {
                answers.add(getQuestionProcessor(question).answerQuestion(question.getQuestionText()));
            } catch (ParserException e) {
                answers.add(MESSAGE);
            }
        return answers;
    }

    private QuestionProcessor getQuestionProcessor(Question question) throws ParserException {
        QuestionProcessor processor = processors.get(question.getQuestionType());
        if (processor == null) {
            throw new ParserException();
        }
        return processor;
    }
}
