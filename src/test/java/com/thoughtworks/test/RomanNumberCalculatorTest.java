package com.thoughtworks.test;

import org.junit.Test;

import static com.thoughtworks.test.RomanNumber.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RomanNumberCalculatorTest {

    @Test
    public void shouldCalculateVIAs6() {
        RomanNumberCalculator objectUnderTest = new RomanNumberCalculator();
        int result = objectUnderTest.calculate(V, I);
        assertThat(result, is(6));
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
    public void shouldCalculateIVAs4() {
        RomanNumberCalculator objectUnderTest = new RomanNumberCalculator();
        int result = objectUnderTest.calculate(I, V);
        assertThat(result, is(4));
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
    public void shouldThrowAnExceptionForDD() {
        RomanNumberCalculator objectUnderTest = new RomanNumberCalculator();
        objectUnderTest.calculate(D, D);
    }

//    @Test(expected = IllegalArgumentException.class)
//    public void shouldThrowAnExceptionForIIV() {
//        RomanNumberCalculator objectUnderTest = new RomanNumberCalculator();
//        objectUnderTest.calculate(I, I, V);
//    }

}