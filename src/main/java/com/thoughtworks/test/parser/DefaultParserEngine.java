package com.thoughtworks.test.parser;

import com.thoughtworks.test.Logger;
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

    public void parseInput(InputReader inputReader) {
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

    private void parseLine(String line) {
        for (ReadParser parser : parsers) {
            try {
                if (parser.parse(line.trim())) {
                    break;
                }
            } catch (ParserException e) {
                LOGGER.log("Issue with parsing: " + line);
            }
        }
    }
}
