package com.thoughtworks.test;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class IntegrationTest {

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{
                {"glob is I" + System.lineSeparator()
                        + "prok is V" + System.lineSeparator()
                        + "pish is X" + System.lineSeparator()
                        + "tegj is L" + System.lineSeparator()
                        + "glob glob Silver is 34 Credits" + System.lineSeparator()
                        + "glob prok Gold is 57800 Credits" + System.lineSeparator()
                        + "pish pish Iron is 3910 Credits" + System.lineSeparator()
                        + " how much is pish tegj glob glob ?" + System.lineSeparator()
                        + "how many Credits is glob prok Silver ?" + System.lineSeparator()
                        + "how many Credits is glob prok Gold ?" + System.lineSeparator()
                        + "how many Credits is glob prok Iron ?" + System.lineSeparator()
                        + "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?" + System.lineSeparator()
                        + "how many Credits is glob prok Grzyb ?" + System.lineSeparator()
                        + "how much is piri piri ?" + System.lineSeparator()
                        + "how many is piri piri ?" + System.lineSeparator(),
                        "pish tegj glob glob is 42" + System.lineSeparator()
                                + "glob prok Silver is 68 Credits" + System.lineSeparator()
                                + "glob prok Gold is 57800 Credits" + System.lineSeparator()
                                + "glob prok Iron is 782 Credits" + System.lineSeparator()
                                + "I have no idea what you are talking about" + System.lineSeparator()
                                + "I have no idea what you are talking about" + System.lineSeparator()
                                + "I have no idea what you are talking about" + System.lineSeparator()
                                + "I have no idea what you are talking about" + System.lineSeparator()
                },
                {"glob is I" + System.lineSeparator()
                        + "prok is X" + System.lineSeparator()
                        + "glob glob Silver is 34 Credits" + System.lineSeparator()
                        + "how many Credits is glob prok Silver ?" + System.lineSeparator()
                        + " how much is glob ?" + System.lineSeparator(),
                        "glob prok Silver is 153 Credits" + System.lineSeparator()
                                + "glob is 1" + System.lineSeparator()
                }
        });
    }

    private String input;
    private String expectedOutput;

    public IntegrationTest(String input, String expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void shouldParseInputProperly() throws IOException {

        File testFile = temporaryFolder.newFile("testFile");
        FileUtils.writeStringToFile(testFile, input, Charset.defaultCharset());

        DefaultRunner runner = new DefaultRunner();
        runner.run(testFile.getAbsolutePath());
        assertThat(outContent.toString(), is(expectedOutput));
    }


}