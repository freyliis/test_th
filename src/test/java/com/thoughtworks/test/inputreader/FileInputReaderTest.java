package com.thoughtworks.test.inputreader;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FileInputReaderTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionForNullFileName() throws IOException {
        new FileInputReader(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionForEmptyFileName() throws IOException {
        new FileInputReader("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionForNotExistingFile() throws IOException {
        new FileInputReader("file");
    }

    @Test
    public void shouldReturnFalseForEmptyFile() throws IOException {
        File testFile = temporaryFolder.newFile("testfile");
        FileInputReader objectUnderTest = new FileInputReader(testFile.getAbsolutePath());
        boolean result = objectUnderTest.hasMoreInput();
        assertFalse(result);
    }

    @Test
    public void shouldReadInputFromTemporaryFile() throws IOException {
        File testFile = temporaryFolder.newFile("testfile");
        String text = "test";
        FileUtils.writeStringToFile(testFile, text, Charset.defaultCharset());
        FileInputReader objectUnderTest = new FileInputReader(testFile.getAbsolutePath());
        assertTrue(objectUnderTest.hasMoreInput());
        String result = objectUnderTest.readInput();
        assertThat(result, is(text));
    }

}