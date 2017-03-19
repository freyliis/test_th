package com.thoughtworks.test.parser;

import com.thoughtworks.test.Logger;

import java.util.List;

public class Parser {

    private static final Logger LOGGER = new Logger(Parser.class.getName());
    private final List<ReadParser> parsers;

    public Parser(List<ReadParser> parsers) {
        this.parsers = parsers;
    }

    public void parse(String inputText) {
        if (inputText == null || inputText.isEmpty()) {
            throw new IllegalArgumentException();
        }
        String[] lines = inputText.split("\n");
        for (String line : lines) {
            parseLine(line);
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
