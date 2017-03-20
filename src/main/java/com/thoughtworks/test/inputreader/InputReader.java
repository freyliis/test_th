package com.thoughtworks.test.inputreader;

public interface InputReader {

    String readInput() throws ReadException;

    boolean hasMoreInput() throws ReadException;
}
