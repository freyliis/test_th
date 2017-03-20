package com.thoughtworks.test.configuration;

import com.thoughtworks.test.parser.engine.ParserEngine;
import com.thoughtworks.test.question.QuestionList;
import com.thoughtworks.test.question.engine.QuestionEngine;

public interface Configuration {

    ParserEngine createParserEngine(QuestionList questionMap);

    QuestionEngine createQuestionEngine();

    QuestionList createQuestionList();
}
