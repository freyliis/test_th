package com.thoughtworks.test.question;

import com.thoughtworks.test.parser.ParserException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionsProcessor {

    private final Map<String, QuestionProcessor> processors = new HashMap<>();

    public QuestionsProcessor(Map<String, QuestionProcessor> processors) {
        this.processors.putAll(processors);
    }

    public List<String> processQuestions(QuestionMap questionMap) {
        List<String> answers = new ArrayList<>();
        List<Question> questionsForRegex = questionMap.getQuestions();
        for (Question question : questionsForRegex)
            try {
                answers.add(getQuestionProcessor(question).answerQuestion(question.getQuestionText()));
            } catch (ParserException e) {
                answers.add(e.getMessage());
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
