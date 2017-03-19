package com.thoughtworks.test.question;

import com.thoughtworks.test.parser.ParserException;
import com.thoughtworks.test.parser.ReadParser;

public class QuestionParser implements ReadParser {

    private final String questionRegex;
    private final QuestionMap questionMap;

    public QuestionParser(String questionRegex, QuestionMap questionMap) {
        this.questionRegex = questionRegex;
        this.questionMap = questionMap;
    }

    public boolean parse(String inputText) throws ParserException {
        if (inputText.matches(getQuestionRegex(questionRegex))) {
            String questionValues = inputText.replace(questionRegex, "").replace("?", "").trim();
            questionMap.addQuestion(questionRegex, questionValues);
            return true;
        }
        return false;
    }

    private String getQuestionRegex(String questionPattern) {
        return "^" + questionPattern + ".*" + SEPARATOR + "?$";
    }
}
