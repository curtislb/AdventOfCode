package com.curtislb.adventofcode.common.math

import com.curtislb.adventofcode.common.testing.assertContainsExactly
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests common digit-related functions and utilities.
 */
class DigitsTest {
    @Test
    fun testDecimalDigits() {
        assertContainsExactly(listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), DECIMAL_DIGITS)
    }

    @Test
    fun testNumeralToDigit() {
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

    @Test
    fun testUppercaseLetterToDigit() {
        assertEquals(10, 'A'.toDigit())
        assertEquals(11, 'B'.toDigit())
        assertEquals(12, 'C'.toDigit())
        assertEquals(13, 'D'.toDigit())
        assertEquals(14, 'E'.toDigit())
        assertEquals(15, 'F'.toDigit())
        assertEquals(16, 'G'.toDigit())
        assertEquals(17, 'H'.toDigit())
        assertEquals(18, 'I'.toDigit())
        assertEquals(33, 'X'.toDigit())
        assertEquals(34, 'Y'.toDigit())
        assertEquals(35, 'Z'.toDigit())
    }

    @Test
    fun testLowercaseLetterToDigit() {
        assertEquals(10, 'a'.toDigit())
        assertEquals(11, 'b'.toDigit())
        assertEquals(12, 'c'.toDigit())
        assertEquals(13, 'd'.toDigit())
        assertEquals(14, 'e'.toDigit())
        assertEquals(15, 'f'.toDigit())
        assertEquals(16, 'g'.toDigit())
        assertEquals(17, 'h'.toDigit())
        assertEquals(18, 'i'.toDigit())
        assertEquals(33, 'x'.toDigit())
        assertEquals(34, 'y'.toDigit())
        assertEquals(35, 'z'.toDigit())
    }

    @Test
    fun testInvalidCharToDigit() {
        assertThrows<IllegalArgumentException> { '/'.toDigit() }
        assertThrows<IllegalArgumentException> { ':'.toDigit() }
        assertThrows<IllegalArgumentException> { '@'.toDigit() }
        assertThrows<IllegalArgumentException> { '['.toDigit() }
        assertThrows<IllegalArgumentException> { '`'.toDigit() }
        assertThrows<IllegalArgumentException> { '{'.toDigit() }
        assertThrows<IllegalArgumentException> { '!'.toDigit() }
        assertThrows<IllegalArgumentException> { '?'.toDigit() }
        assertThrows<IllegalArgumentException> { '$'.toDigit() }
        assertThrows<IllegalArgumentException> { '#'.toDigit() }
        assertThrows<IllegalArgumentException> { '.'.toDigit() }
        assertThrows<IllegalArgumentException> { ','.toDigit() }
        assertThrows<IllegalArgumentException> { ' '.toDigit() }
        assertThrows<IllegalArgumentException> { '\t'.toDigit() }
        assertThrows<IllegalArgumentException> { '\r'.toDigit() }
        assertThrows<IllegalArgumentException> { '\n'.toDigit() }
    }
}
