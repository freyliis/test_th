package com.thoughtworks.test;

public class Main {

    public static void main(String[] args) {
        Runner runner = new DefaultRunner();
        if (args.length == 1) {
            runner.run(args[0]);
        }
    }

}
