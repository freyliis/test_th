package com.thoughtworks.test.parser;

import java.util.List;

public class QuestionParser implements ReadParser {

    private final String questionRegex;
    private final List<String> questions;

    public QuestionParser(String questionRegex, List<String> questions) {
        this.questionRegex = questionRegex;
        this.questions = questions;
    }

    public boolean parse(String inputText) throws ParserException {
        if (inputText.matches(getQuestionRegex(questionRegex))) {
            String questionValues = inputText.replace(questionRegex, "").replace("?", "").trim();
            questions.add(questionValues);
            return true;
        }
        return false;
    }

    private String getQuestionRegex(String questionPattern) {
        return "^" + questionPattern + ".*" + SEPARATOR + "?$";
    }
}
