package com.thoughtworks.test;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RomanNumberTest {

    @Test
    public void shouldReturnProperValuesForRomanNumbers(){
        assertThat(RomanNumber.I.getValue(), is(1));
        assertThat(RomanNumber.V.getValue(), is(5));
        assertThat(RomanNumber.X.getValue(), is(10));
        assertThat(RomanNumber.L.getValue(), is(50));
        assertThat(RomanNumber.C.getValue(), is(100));
        assertThat(RomanNumber.D.getValue(), is(500));
        assertThat(RomanNumber.M.getValue(), is(1000));
    }

}