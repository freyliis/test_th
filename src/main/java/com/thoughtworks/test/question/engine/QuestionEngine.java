package com.thoughtworks.test.question.engine;

import com.thoughtworks.test.question.QuestionList;

import java.util.List;

public interface QuestionEngine<T> {

    List<T> processQuestions(QuestionList questionMap);
}
