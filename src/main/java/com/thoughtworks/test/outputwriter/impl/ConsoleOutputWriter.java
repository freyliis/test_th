package com.thoughtworks.test.outputwriter.impl;

import com.thoughtworks.test.outputwriter.OutputWriter;

import java.util.List;

public class ConsoleOutputWriter implements OutputWriter {

    public void writeOutput(List<String> answers) {
        answers.stream().forEachOrdered(s -> System.out.println(s));
    }
}
