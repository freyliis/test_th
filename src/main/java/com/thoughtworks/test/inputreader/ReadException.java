package com.thoughtworks.test.inputreader;

import java.io.IOException;

public class ReadException extends Exception {
    public ReadException(IOException e) {
        super("", e);
    }

    public ReadException(String message) {
        super(message);
    }

    public ReadException(String message, IOException e) {
        super(message, e);
    }
}
