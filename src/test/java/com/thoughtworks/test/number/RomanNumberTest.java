package com.thoughtworks.test.number;

import com.thoughtworks.test.number.RomanNumber;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RomanNumberTest {

    @Test
    public void shouldReturnProperValuesForRomanNumbers() {
        assertThat(RomanNumber.I.getValue(), is(1));
        assertThat(RomanNumber.V.getValue(), is(5));
        assertThat(RomanNumber.X.getValue(), is(10));
        assertThat(RomanNumber.L.getValue(), is(50));
        assertThat(RomanNumber.C.getValue(), is(100));
        assertThat(RomanNumber.D.getValue(), is(500));
        assertThat(RomanNumber.M.getValue(), is(1000));
    }

    @Test
    public void shouldReturnTrueIfICanBeSubtractedFromV() {
        assertTrue(RomanNumber.V.canSubtract(RomanNumber.I));
    }

    @Test
    public void shouldReturnTrueIfICanBeSubtractedFromX() {
        assertTrue(RomanNumber.X.canSubtract(RomanNumber.I));
    }

    @Test
    public void shouldReturnTrueIfXCanBeSubtractedFromL() {
        assertTrue(RomanNumber.L.canSubtract(RomanNumber.X));
    }

    @Test
    public void shouldReturnTrueIfXCanBeSubtractedFromC() {
        assertTrue(RomanNumber.C.canSubtract(RomanNumber.X));
    }
    @Test
    public void shouldReturnTrueIfCCanBeSubtractedFromD() {
        assertTrue(RomanNumber.D.canSubtract(RomanNumber.C));
    }
    @Test
    public void shouldReturnTrueIfCCanBeSubtractedFromM() {
        assertTrue(RomanNumber.M.canSubtract(RomanNumber.C));
    }

    @Test
    public void shouldReturnFalseNothingCanBeExtractedFromI() {
        assertFalse(RomanNumber.I.canSubtract(RomanNumber.X));
    }

}
