package com.thoughtworks.test;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static com.thoughtworks.test.RomanNumber.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DefaultRomanNumberCalculatorTest {

    private DefaultRomanNumberCalculator objectUnderTest;

    @Before
    public void setUp() throws Exception {
        objectUnderTest = new DefaultRomanNumberCalculator();
    }

    @Test
    public void shouldCalculateVIIIAs8() {
        int result = objectUnderTest.calculate(Arrays.asList(V, I, I, I));
        assertThat(result, is(8));
    }

    @Test
    public void shouldCalculateVIIAs7() {
        int result = objectUnderTest.calculate(Arrays.asList(V, I, I));
        assertThat(result, is(7));
    }

    @Test
    public void shouldCalculateXXXIIAs32() {
        int result = objectUnderTest.calculate(Arrays.asList(X, X, X, I, I));
        assertThat(result, is(32));
    }

    @Test
    public void shouldCalculateIIIAs3() {
        int result = objectUnderTest.calculate(Arrays.asList(I, I, I));
        assertThat(result, is(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionForMoreThanThreeI() {
        objectUnderTest.calculate(Arrays.asList(I, I, I, I));
    }

    @Test
    public void shouldCalculateXXXAs30() {
        int result = objectUnderTest.calculate(Arrays.asList(X, X, X));
        assertThat(result, is(30));
    }

    @Test
    public void shouldCalculateCCCAs300() {
        int result = objectUnderTest.calculate(Arrays.asList(C, C, C));
        assertThat(result, is(300));
    }

    @Test
    public void shouldCalculateMMMAs3000() {
        int result = objectUnderTest.calculate(Arrays.asList(M, M, M));
        assertThat(result, is(3000));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionForVV() {
        objectUnderTest.calculate(Arrays.asList(V, V));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionForLL() {
        objectUnderTest.calculate(Arrays.asList(L, L));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionForDD() {
        objectUnderTest.calculate(Arrays.asList(D, D));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionForIIIVI() {
        objectUnderTest.calculate(Arrays.asList(I, I, I, V, I));
    }

    @Test
    public void shouldCalculateIVAs4() {
        int result = objectUnderTest.calculate(Arrays.asList(I, V));
        assertThat(result, is(4));
    }

    @Test
    public void shouldCalculateIXAs9() {
        int result = objectUnderTest.calculate(Arrays.asList(I, X));
        assertThat(result, is(9));
    }

    @Test
    public void shouldCalculateXLAs40() {
        int result = objectUnderTest.calculate(Arrays.asList(X, L));
        assertThat(result, is(40));
    }

    @Test
    public void shouldCalculateXCAs90() {
        int result = objectUnderTest.calculate(Arrays.asList(X, C));
        assertThat(result, is(90));
    }

    @Test
    public void shouldCalculateCDAs400() {
        int result = objectUnderTest.calculate(Arrays.asList(C, D));
        assertThat(result, is(400));
    }

    @Test
    public void shouldCalculateCMAs900() {
        int result = objectUnderTest.calculate(Arrays.asList(C, M));
        assertThat(result, is(900));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionForIL() {
        objectUnderTest.calculate(Arrays.asList(I, L));
    }

}