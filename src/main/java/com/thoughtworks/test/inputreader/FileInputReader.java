package com.thoughtworks.test.inputreader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileInputReader implements InputReader {
    @Override
    public String readInput(String source) throws IOException {
        return new String(Files.readAllBytes(Paths.get(source)));
    }
}
