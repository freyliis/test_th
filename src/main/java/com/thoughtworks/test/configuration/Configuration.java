package com.thoughtworks.test.configuration;

import com.thoughtworks.test.parser.ParserEngine;
import com.thoughtworks.test.question.QuestionEngine;
import com.thoughtworks.test.question.QuestionMap;

public interface Configuration {

    ParserEngine createParserEngine(QuestionMap questionMap);

    QuestionEngine createQuestionEngine();
}
