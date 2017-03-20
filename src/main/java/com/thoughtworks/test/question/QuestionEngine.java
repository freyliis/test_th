package com.thoughtworks.test.question;

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

    public List<String> processQuestions(QuestionList questionMap) {
        List<String> answers = new ArrayList<>();
        List<Question> questionsForRegex = questionMap.getQuestions();
        for (Question question : questionsForRegex) {
            QuestionProcessor questionProcessor = getQuestionProcessor(question);
            if (questionProcessor != null) {
                answers.add(questionProcessor.answerQuestion(question.getQuestionText()));
            } else {
                answers.add(MESSAGE);
            }
        }
        return answers;
    }

    private QuestionProcessor getQuestionProcessor(Question question) {
        return processors.get(question.getQuestionType());
    }
}
