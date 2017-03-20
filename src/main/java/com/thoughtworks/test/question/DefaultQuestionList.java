package com.thoughtworks.test.question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultQuestionList implements QuestionList {

    private List<Question> questions;

    public DefaultQuestionList() {
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public int getSize() {
        return questions.size();
    }

    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);

    }
}
