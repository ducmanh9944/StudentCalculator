package com.example.studentcalculator.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExpressionEvaluatorTest {
    @Test
    public void evaluatesPercentAsPercentageOfPreviousValue() throws Exception {
        assertEquals(110.0, ExpressionEvaluator.evaluate("100+10%"), 1e-9);
        assertEquals(90.0, ExpressionEvaluator.evaluate("100-10%"), 1e-9);
        assertEquals(132.0, ExpressionEvaluator.evaluate("100+20+10%"), 1e-9);
    }

    @Test
    public void supportsImplicitMultiplicationWithPercent() throws Exception {
        assertEquals(6.6, ExpressionEvaluator.evaluate("2*(3+10%)"), 1e-9);
    }
}
