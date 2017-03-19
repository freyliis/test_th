package com.thoughtworks.test.parser;

import java.util.List;

public class HowMuchQuestionParser implements ReadParser {

    private final static String HOW_MUCH_IS_REGEX = "how much is";
    private final List<String> questions;

    public HowMuchQuestionParser(List<String> questions) {
        this.questions = questions;
    }

    public boolean parse(String inputText) throws ParserException {
        if (inputText.matches(getQuestionRegex(HOW_MUCH_IS_REGEX))) {
            String questionValues = inputText.replace(HOW_MUCH_IS_REGEX, "").replace("?", "").trim();
            questions.add(questionValues);
            return true;
        }
        return false;
    }

    private String getQuestionRegex(String questionPattern) {
        return "^" + questionPattern + SEPARATOR + ".*" + SEPARATOR + "?$";
    }
}
