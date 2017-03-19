package com.thoughtworks.test.question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionMap {

    private List<Question> questionMap;

    public QuestionMap() {
        this.questionMap = new ArrayList<>();
    }

    public void addQuestion(String regex, String question) {
        questionMap.add(new Question(regex, question));
    }

    public int getSize() {
        return questionMap.size();
    }

    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questionMap);

    }
}
