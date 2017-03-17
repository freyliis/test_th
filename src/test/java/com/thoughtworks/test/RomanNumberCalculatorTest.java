package com.thoughtworks.test;

import org.junit.Test;

import static com.thoughtworks.test.RomanNumber.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RomanNumberCalculatorTest {

    @Test
    public void shouldCalculateVIIIAs8() {
        RomanNumberCalculator objectUnderTest = new RomanNumberCalculator();
        int result = objectUnderTest.calculate(V, I, I, I);
        assertThat(result, is(8));
    }

    @Test
    public void shouldCalculateVIIAs7() {
        RomanNumberCalculator objectUnderTest = new RomanNumberCalculator();
        int result = objectUnderTest.calculate(V, I, I);
        assertThat(result, is(7));
    }

    @Test
    public void shouldCalculateXXXIIAs32() {
        RomanNumberCalculator objectUnderTest = new RomanNumberCalculator();
        int result = objectUnderTest.calculate(X, X, X, I, I);
        assertThat(result, is(32));
    }

    @Test
    public void shouldCalculateIIIAs3() {
        RomanNumberCalculator objectUnderTest = new RomanNumberCalculator();
        int result = objectUnderTest.calculate(I, I, I);
        assertThat(result, is(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionForMoreThanThreeI() {
        RomanNumberCalculator objectUnderTest = new RomanNumberCalculator();
        objectUnderTest.calculate(I, I, I, I);
    }

    @Test
    public void shouldCalculateXXXAs30() {
        RomanNumberCalculator objectUnderTest = new RomanNumberCalculator();
        int result = objectUnderTest.calculate(X, X, X);
        assertThat(result, is(30));
    }

    @Test
    public void shouldCalculateCCCAs300() {
        RomanNumberCalculator objectUnderTest = new RomanNumberCalculator();
        int result = objectUnderTest.calculate(C, C, C);
        assertThat(result, is(300));
    }

    @Test
    public void shouldCalculateMMMAs3000() {
        RomanNumberCalculator objectUnderTest = new RomanNumberCalculator();
        int result = objectUnderTest.calculate(M, M, M);
        assertThat(result, is(3000));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionForVV() {
        RomanNumberCalculator objectUnderTest = new RomanNumberCalculator();
        objectUnderTest.calculate(V, V);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionForLL() {
        RomanNumberCalculator objectUnderTest = new RomanNumberCalculator();
        objectUnderTest.calculate(L, L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionForDD() {
        RomanNumberCalculator objectUnderTest = new RomanNumberCalculator();
        objectUnderTest.calculate(D, D);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionForIIIVI() {
        RomanNumberCalculator objectUnderTest = new RomanNumberCalculator();
        objectUnderTest.calculate(I, I, I, V, I);
    }

    @Test
    public void shouldCalculateIVAs4() {
        RomanNumberCalculator objectUnderTest = new RomanNumberCalculator();
        int result = objectUnderTest.calculate(I, V);
        assertThat(result, is(4));
    }

    @Test
    public void shouldCalculateIXAs9() {
        RomanNumberCalculator objectUnderTest = new RomanNumberCalculator();
        int result = objectUnderTest.calculate(I, X);
        assertThat(result, is(9));
    }

    @Test
    public void shouldCalculateXLAs40() {
        RomanNumberCalculator objectUnderTest = new RomanNumberCalculator();
        int result = objectUnderTest.calculate(X, L);
        assertThat(result, is(40));
    }

    @Test
    public void shouldCalculateXCAs90() {
        RomanNumberCalculator objectUnderTest = new RomanNumberCalculator();
        int result = objectUnderTest.calculate(X, C);
        assertThat(result, is(90));
    }

    @Test
    public void shouldCalculateCDAs400() {
        RomanNumberCalculator objectUnderTest = new RomanNumberCalculator();
        int result = objectUnderTest.calculate(C, D);
        assertThat(result, is(400));
    }

    @Test
    public void shouldCalculateCMAs900() {
        RomanNumberCalculator objectUnderTest = new RomanNumberCalculator();
        int result = objectUnderTest.calculate(C, M);
        assertThat(result, is(900));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionForIL() {
        RomanNumberCalculator objectUnderTest = new RomanNumberCalculator();
        objectUnderTest.calculate(I, L);
    }

}