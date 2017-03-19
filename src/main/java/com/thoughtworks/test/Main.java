package com.thoughtworks.test;

import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws URISyntaxException {
        Runner runner = new DefaultRunner();
        if (args.length == 1) {
            runner.run(args[0]);
        }
    }

}
