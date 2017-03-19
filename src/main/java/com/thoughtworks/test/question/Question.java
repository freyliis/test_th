package com.thoughtworks.test.question;

public class Question {

    private final String questionType;
    private final String questionText;

    public Question(String questionType, String questionText) {
        this.questionType = questionType;
        this.questionText = questionText;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getQuestionType() {
        return questionType;
    }
}
