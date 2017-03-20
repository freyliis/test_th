package com.thoughtworks.test.inputreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileLineByLineInputReader implements InputReader {
    private BufferedReader br;
    private String line;

    public FileLineByLineInputReader(String filePath) throws ReadException {
        Path path = getPathIfFileIsCorrect(filePath);
        try {
            br = Files.newBufferedReader(path);
        } catch (IOException e) {
            throw new ReadException(e);
        }
    }

    private Path getPathIfFileIsCorrect(String filePath) throws ReadException {
        if (filePath == null || !Files.exists(Paths.get(filePath))) {
            throw new ReadException("File path cannot be null/empty and file has to exist. Wrong path file: " + filePath);
        }
        Path path = Paths.get(filePath);
        if (Files.isDirectory(path)) {
            throw new ReadException("Cannot read directory: " + filePath);
        }
        return path;
    }

    @Override
    public String readInput() {
        return line;
    }

    @Override
    public boolean hasMoreInput() throws ReadException {
        try {
            boolean hasMoreInput = (line = br.readLine()) != null;
            if (!hasMoreInput) {
                br.close();
            }
            return hasMoreInput;
        } catch (IOException e) {
            throw new ReadException(e);
        }
    }
}
