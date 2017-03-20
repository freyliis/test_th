package com.thoughtworks.test.configuration;

import com.thoughtworks.test.parser.DefaultParserEngine;
import com.thoughtworks.test.question.QuestionMap;
import com.thoughtworks.test.question.QuestionEngine;

public interface Configuration {

    DefaultParserEngine getParserEngine(QuestionMap questionMap);

    QuestionEngine getQuestionsProcessor();
}
