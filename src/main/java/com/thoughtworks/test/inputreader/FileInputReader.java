package com.thoughtworks.test.inputreader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileInputReader implements InputReader {
    private List<String> lines;
    private int counter = 0;

    public FileInputReader(String filePath) {
        if (filePath == null || !Files.exists(Paths.get(filePath))) {
            throw new IllegalArgumentException("File path cannot be null/empty and file has to exist. Wrong path file: " + filePath);
        }
        try {
            lines = Files.lines(Paths.get(filePath)).collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalArgumentException("Issue with file", e);
        }
    }

    @Override
    public String readInput() {
        return lines.get(counter++);
    }

    @Override
    public boolean hasMoreInput() {
        return (lines.size() > counter);
    }
}
