package com.curtislb.adventofcode.common.math

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests digit utilities from [com.curtislb.adventofcode.common.math].
 */
class DigitsTest {
    @Test fun testDigit() {
        assertEquals(0, 0.digit(0))
        assertEquals(0, 0.digit(1))
        assertEquals(0, 0.digit(0, base = 2))
        assertEquals(0, 0.digit(1, base = 16))
        assertEquals(1, 1.digit(0))
        assertEquals(0, 1.digit(1))
        assertEquals(1, 1.digit(0, base = 8))
        assertEquals(0, 1.digit(1, base = 256))
        assertEquals(7, 7.digit(0))
        assertEquals(0, 7.digit(1))
        assertEquals(0, 7.digit(2))
        assertEquals(1, 7.digit(0, base = 3))
        assertEquals(2, 7.digit(1, base = 3))
        assertEquals(0, 7.digit(2, base = 3))
        assertEquals(9, 859.digit(0))
        assertEquals(5, 859.digit(1))
        assertEquals(8, 859.digit(2))
        assertEquals(0, 859.digit(3))
        assertEquals(11, 859.digit(0, base = 16))
        assertEquals(5, 859.digit(1, base = 16))
        assertEquals(3, 859.digit(2, base = 16))
        assertEquals(0, 859.digit(3, base = 16))
        assertEquals(91, 859.digit(0, base = 256))
        assertEquals(3, 859.digit(1, base = 256))
        assertEquals(0, 859.digit(2, base = 256))
    }

    @Test fun testToDigitForValidChar() {
        assertEquals(0, '0'.toDigit())
        assertEquals(1, '1'.toDigit())
        assertEquals(2, '2'.toDigit())
        assertEquals(3, '3'.toDigit())
        assertEquals(4, '4'.toDigit())
        assertEquals(5, '5'.toDigit())
        assertEquals(6, '6'.toDigit())
        assertEquals(7, '7'.toDigit())
        assertEquals(8, '8'.toDigit())
        assertEquals(9, '9'.toDigit())
    }

    @Test(expected = IllegalArgumentException::class)
    fun testToDigitForInvalidChar() {
        'A'.toDigit()
    }
}
