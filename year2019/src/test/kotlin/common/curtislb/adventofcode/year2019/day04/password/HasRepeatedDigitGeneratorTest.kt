package com.curtislb.adventofcode.year2019.day04.password

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Tests [HasRepeatedDigitGenerator].
 */
class HasRepeatedDigitGeneratorTest {
    @Test
    fun testWithNoRepeatedDigit() {
        var generator: PasswordGenerator = HasRepeatedDigitGenerator()
        assertFalse(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = HasRepeatedDigitGenerator().addDigit(0)
        assertFalse(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = HasRepeatedDigitGenerator().addDigit(5).addDigit(3)
        assertFalse(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = HasRepeatedDigitGenerator().addDigit(2).addDigit(5).addDigit(7)
        assertFalse(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = HasRepeatedDigitGenerator().addDigit(9).addDigit(3).addDigit(9)
        assertFalse(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = HasRepeatedDigitGenerator().addDigit(6).addDigit(0).addDigit(9).addDigit(1)
        assertFalse(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = HasRepeatedDigitGenerator().addDigit(4).addDigit(7).addDigit(4).addDigit(7)
        assertFalse(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)
    }

    @Test
    fun testWithRepeatedDigit() {
        var generator: PasswordGenerator = HasRepeatedDigitGenerator().addDigit(4).addDigit(4)
        assertTrue(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = HasRepeatedDigitGenerator().addDigit(1).addDigit(1).addDigit(1)
        assertTrue(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = HasRepeatedDigitGenerator().addDigit(6).addDigit(6).addDigit(8)
        assertTrue(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = HasRepeatedDigitGenerator().addDigit(4).addDigit(0).addDigit(0)
        assertTrue(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = HasRepeatedDigitGenerator().addDigit(3).addDigit(3).addDigit(3).addDigit(3)
        assertTrue(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = HasRepeatedDigitGenerator().addDigit(2).addDigit(2).addDigit(2).addDigit(1)
        assertTrue(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = HasRepeatedDigitGenerator().addDigit(5).addDigit(9).addDigit(9).addDigit(9)
        assertTrue(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = HasRepeatedDigitGenerator().addDigit(8).addDigit(8).addDigit(0).addDigit(2)
        assertTrue(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = HasRepeatedDigitGenerator().addDigit(4).addDigit(5).addDigit(5).addDigit(3)
        assertTrue(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = HasRepeatedDigitGenerator().addDigit(7).addDigit(9).addDigit(7).addDigit(7)
        assertTrue(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)
    }
}
