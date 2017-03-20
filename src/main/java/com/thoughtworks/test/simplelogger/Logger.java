package com.thoughtworks.test.simplelogger;

public class Logger {

    private final String className;

    public Logger(String className) {
        this.className = className;
    }

    public void log(String message) {
        System.out.println(className + " " + message);
    }

    public void logError(String message) {
        System.err.println(className + " " + message);
    }
}
