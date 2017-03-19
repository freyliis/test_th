package com.thoughtworks.test.inputreader;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FileInputReaderTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void shouldReadInputFromTemporaryFile() throws IOException {
        File testFile = temporaryFolder.newFile("testfile");
        String text = "test";
        FileUtils.writeStringToFile(testFile, text, Charset.defaultCharset());
        FileInputReader objectUnderTest = new FileInputReader();
        String result = objectUnderTest.readInput(testFile.getAbsolutePath());
        assertThat(result, is(text));
    }

}