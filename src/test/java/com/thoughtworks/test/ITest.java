package com.thoughtworks.test;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ITest {

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
        FileUtils.writeStringToFile(testFile, "glob is I\n"
                + "prok is V\n"
                + "pish is X\n"
                + "tegj is L\n"
                + "glob glob Silver is 34 Credits\n"
                + "glob prok Gold is 57800 Credits\n"
                + "pish pish Iron is 3910 Credits\n"
                + " how much is pish tegj glob glob ?\n"
                + "how many Credits is glob prok Silver ?\n"
                + "how many Credits is glob prok Gold ?\n"
                + "how many Credits is glob prok Iron ?\n"
                + "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?", Charset.defaultCharset());

        DefaultRunner runner = new DefaultRunner();
        runner.run(testFile.getAbsolutePath());
        assertThat(outContent.toString(), is("pish tegj glob glob is 42\r\n"
                + "glob prok Silver is 68 Credits\r\n"
                + "glob prok Gold is 57800 Credits\r\n"
                + "glob prok Iron is 782 Credits\r\n"
                + "I have no idea what you are talking about\r\n"));
    }

}