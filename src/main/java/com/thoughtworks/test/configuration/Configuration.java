package com.thoughtworks.test.configuration;

import com.thoughtworks.test.parser.ParserEngine;
import com.thoughtworks.test.question.QuestionEngine;
import com.thoughtworks.test.question.DefaultQuestionList;

public interface Configuration {

    ParserEngine createParserEngine(DefaultQuestionList questionMap);

    QuestionEngine createQuestionEngine();
}
