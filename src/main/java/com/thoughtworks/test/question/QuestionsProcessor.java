package com.thoughtworks.test.question;

import java.util.List;

public class QuestionsProcessor {

    private final List<QuestionProcessor> questionProcessorList;

    public QuestionsProcessor(List<QuestionProcessor> questionProcessorList) {
        this.questionProcessorList = questionProcessorList;
    }

    public List<String> processQuestions(List<String> questions) {
        return null;
    }
}
