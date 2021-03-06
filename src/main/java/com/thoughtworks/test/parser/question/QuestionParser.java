package com.thoughtworks.test.parser.question;

import com.thoughtworks.test.parser.ParserException;
import com.thoughtworks.test.parser.ReadParser;
import com.thoughtworks.test.question.Question;
import com.thoughtworks.test.question.QuestionList;

import static com.thoughtworks.test.configuration.impl.DefaultConfiguration.SEPARATOR;

public class QuestionParser implements ReadParser {

    private final String questionRegex;
    private final QuestionList questionMap;

    public QuestionParser(String questionRegex, QuestionList questionMap) {
        this.questionRegex = questionRegex;
        this.questionMap = questionMap;
    }

    public boolean parse(String inputText) throws ParserException {
        if (inputText.matches(getQuestionRegex(questionRegex))) {
            String questionValues = inputText.replace(questionRegex, "").replace("?", "").trim();
            questionMap.addQuestion(new Question(questionRegex, questionValues));
            return true;
        }
        return false;
    }

    private String getQuestionRegex(String questionPattern) {
        return "^" + questionPattern + ".*" + SEPARATOR + "?$";
    }
}
