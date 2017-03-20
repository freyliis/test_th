package com.thoughtworks.test.configuration;

import com.thoughtworks.test.parser.ParserEngine;
import com.thoughtworks.test.question.QuestionEngine;
import com.thoughtworks.test.question.QuestionList;

public interface Configuration {

    ParserEngine createParserEngine(QuestionList questionMap);

    QuestionEngine createQuestionEngine();

    QuestionList createQuestionList();
}
