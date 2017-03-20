package com.thoughtworks.test.parser.engine;

import com.thoughtworks.test.inputreader.InputReader;
import com.thoughtworks.test.parser.ParserException;

public interface ParserEngine {

    void parseInput(InputReader inputReader) throws ParserException;
}
