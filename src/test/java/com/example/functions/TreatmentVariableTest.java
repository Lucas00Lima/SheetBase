package com.example.functions;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreatmentVariableTest {

    private final TreatmentVariable parser = new TreatmentVariable();

    @Test
    public void testEmptyString() {
        assertEquals(0, parser.variavelValue(""));
    }

    @Test
    public void testNullValue() {
        assertEquals(0, parser.variavelValue(null));
    }

    @Test
    public void testSimpleInteger() {
        assertEquals(12300, parser.variavelValue("123"));
    }

    @Test
    public void testCommaDecimalSeparator() {
        assertEquals(12345, parser.variavelValue("123,45"));
    }

    @Test
    public void testDotDecimalSeparator() {
        assertEquals(12345, parser.variavelValue("123.45"));
    }

    @Test
    public void testThousandSeparatorAndDecimal() {
        assertEquals(123456, parser.variavelValue("1.234,56"));
    }

    @Test
    public void testSingleDigit() {
        assertEquals(900, parser.variavelValue("9"));
    }

    @Test
    public void testTwoDigits() {
        assertEquals(9900, parser.variavelValue("99"));
    }

    @Test
    public void testSingleDecimalPlace() {
        assertEquals(11890, parser.variavelValue("118,9"));
    }

    @Test
    public void testStandardDecimalFormat() {
        assertEquals(11890, parser.variavelValue("118,90"));
    }

    @Test
    public void testInvalidFormat() {
        assertEquals(0, parser.variavelValue("abc"));
    }

    @Test
    public void testMixedCharacters() {
        assertEquals(0, parser.variavelValue("123abc"));
    }
}
