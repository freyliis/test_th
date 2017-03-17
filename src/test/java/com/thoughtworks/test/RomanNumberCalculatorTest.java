package com.thoughtworks.test;

import org.junit.Test;

import static com.thoughtworks.test.RomanNumber.I;
import static com.thoughtworks.test.RomanNumber.V;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RomanNumberCalculatorTest {

    @Test
    public void shouldAddThreeI() {
        RomanNumberCalculator objectUnderTest = new RomanNumberCalculator();
        int result = objectUnderTest.calculate(I, I, I);
        assertThat(result, is(3));
    }
    @Test
    public void shouldAddVAndI() {
        RomanNumberCalculator objectUnderTest = new RomanNumberCalculator();
        int result = objectUnderTest.calculate(V, I);
        assertThat(result, is(6));
    }
    @Test
    public void shouldSubtractIFromV() {
        RomanNumberCalculator objectUnderTest = new RomanNumberCalculator();
        int result = objectUnderTest.calculate(I, V);
        assertThat(result, is(4));
    }

}