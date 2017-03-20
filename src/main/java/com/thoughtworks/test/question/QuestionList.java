package com.thoughtworks.test.question;

import java.util.List;

public interface QuestionList {

    void addQuestion(Question question);

    List<Question> getQuestions();

    int getSize();
}
