package com.thoughtworks.test.parser.engine.impl;

import com.thoughtworks.test.parser.ParserException;
import com.thoughtworks.test.parser.ReadParser;
import com.thoughtworks.test.parser.engine.ParserEngine;
import com.thoughtworks.test.simplelogger.Logger;
import com.thoughtworks.test.inputreader.InputReader;
import com.thoughtworks.test.inputreader.ReadException;

import java.util.List;

public class DefaultParserEngine implements ParserEngine {

    private static final Logger LOGGER = new Logger(DefaultParserEngine.class.getName());
    private final List<ReadParser> parsers;

    public DefaultParserEngine(List<ReadParser> parsers) {
        if (parsers == null) {
            throw new IllegalArgumentException("List of parsers cannot be null");
        }
        this.parsers = parsers;
    }

    public void parseInput(InputReader inputReader) throws ParserException {
        String line = "";
        try {
            while (inputReader.hasMoreInput()) {
                line = inputReader.readInput();
                parseLine(line);
            }
        } catch (ReadException e) {
            LOGGER.log("Issue with parsing: " + line);
        }
    }

    private void parseLine(String line) throws ParserException {
        for (ReadParser parser : parsers) {
            if (parser.parse(line.trim())) {
                break;
            }
        }
    }
}
