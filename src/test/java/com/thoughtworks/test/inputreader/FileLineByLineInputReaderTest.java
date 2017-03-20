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

public class FileLineByLineInputReaderTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test(expected = ReadException.class)
    public void shouldThrowAnExceptionForNullFileName() throws ReadException {
        new FileLineByLineInputReader(null);
    }

    @Test(expected = ReadException.class)
    public void shouldThrowAnExceptionForEmptyFileName() throws ReadException {
        new FileLineByLineInputReader("");
    }

    @Test(expected = ReadException.class)
    public void shouldThrowAnExceptionForNotExistingFile() throws ReadException {
        new FileLineByLineInputReader("file");
    }


    @Test(expected = ReadException.class)
    public void shouldThrowAnExceptionForDirectory() throws IOException, ReadException {
        File testFile = temporaryFolder.newFolder("testfile");
        new FileLineByLineInputReader(testFile.getAbsolutePath());
    }


    @Test
    public void shouldReturnFalseForEmptyFile() throws ReadException, IOException {
        File testFile = temporaryFolder.newFile("testfile");
        FileLineByLineInputReader objectUnderTest = new FileLineByLineInputReader(testFile.getAbsolutePath());
        boolean result = objectUnderTest.hasMoreInput();
        assertFalse(result);
    }

    @Test
    public void shouldReadInputFromTemporaryFile() throws IOException, ReadException {
        File testFile = temporaryFolder.newFile("testfile");
        String text = "test";
        FileUtils.writeStringToFile(testFile, text, Charset.defaultCharset());
        FileLineByLineInputReader objectUnderTest = new FileLineByLineInputReader(testFile.getAbsolutePath());
        assertTrue(objectUnderTest.hasMoreInput());
        String result = objectUnderTest.readInput();
        assertThat(result, is(text));
    }

}