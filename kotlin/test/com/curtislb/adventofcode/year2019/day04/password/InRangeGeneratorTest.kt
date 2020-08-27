package com.curtislb.adventofcode.year2019.day04.password

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Tests [InRangeGenerator].
 */
class InRangeGeneratorTest {
    @Test fun testWithEmptyRange() {
        var generator: PasswordGenerator = InRangeGenerator(1, 0)
        assertFalse(generator.isValid)
        assertEquals(emptySet(), generator.nextDigits)

        generator = InRangeGenerator(2, 1).addDigit(1)
        assertFalse(generator.isValid)
        assertEquals(emptySet(), generator.nextDigits)

        generator = InRangeGenerator(4, 2).addDigit(5).addDigit(5)
        assertFalse(generator.isValid)
        assertEquals(emptySet(), generator.nextDigits)
    }

    @Test fun testWithSingleNumberRange() {
        var generator: PasswordGenerator = InRangeGenerator(0, 0)
        assertTrue(generator.isValid)
        assertEquals(setOf(0), generator.nextDigits)

        generator = InRangeGenerator(0, 0).addDigit(0)
        assertTrue(generator.isValid)
        assertEquals(setOf(0), generator.nextDigits)

        generator = InRangeGenerator(1, 1).addDigit(0)
        assertFalse(generator.isValid)
        assertEquals(setOf(0, 1), generator.nextDigits)
    }

    @Test fun testWithMultipleSmallNumberRange() {
        var generator: PasswordGenerator = InRangeGenerator(0, 1)
        assertTrue(generator.isValid)
        assertEquals(setOf(0, 1), generator.nextDigits)

        generator = InRangeGenerator(0, 1).addDigit(0)
        assertTrue(generator.isValid)
        assertEquals(setOf(0, 1), generator.nextDigits)

        generator = InRangeGenerator(0, 1).addDigit(1)
        assertTrue(generator.isValid)
        assertEquals(emptySet(), generator.nextDigits)

        generator = InRangeGenerator(1, 2)
        assertFalse(generator.isValid)
        assertEquals(setOf(0, 1, 2), generator.nextDigits)

        generator = InRangeGenerator(1, 2).addDigit(0)
        assertFalse(generator.isValid)
        assertEquals(setOf(0, 1, 2), generator.nextDigits)

        generator = InRangeGenerator(1, 2).addDigit(1)
        assertTrue(generator.isValid)
        assertEquals(emptySet(), generator.nextDigits)

        generator = InRangeGenerator(1, 2).addDigit(2)
        assertTrue(generator.isValid)
        assertEquals(emptySet(), generator.nextDigits)
    }

    @Test fun testWithMultipleBigNumberRange() {
        var generator: PasswordGenerator = InRangeGenerator(0, 10)
        assertTrue(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = InRangeGenerator(0, 10).addDigit(1)
        assertTrue(generator.isValid)
        assertEquals(setOf(0), generator.nextDigits)

        generator = InRangeGenerator(0, 10).addDigit(2)
        assertTrue(generator.isValid)
        assertEquals(emptySet(), generator.nextDigits)

        generator = InRangeGenerator(2, 437)
        assertFalse(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = InRangeGenerator(2, 437).addDigit(1)
        assertFalse(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = InRangeGenerator(2, 437).addDigit(2)
        assertTrue(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = InRangeGenerator(2, 437).addDigit(2).addDigit(5)
        assertTrue(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = InRangeGenerator(2, 437).addDigit(2).addDigit(5).addDigit(0)
        assertTrue(generator.isValid)
        assertEquals(emptySet(), generator.nextDigits)

        generator = InRangeGenerator(2, 437).addDigit(4)
        assertTrue(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = InRangeGenerator(2, 437).addDigit(4).addDigit(2)
        assertTrue(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = InRangeGenerator(2, 437).addDigit(4).addDigit(3)
        assertTrue(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7), generator.nextDigits)

        generator = InRangeGenerator(2, 437).addDigit(4).addDigit(4)
        assertTrue(generator.isValid)
        assertEquals(emptySet(), generator.nextDigits)

        generator = InRangeGenerator(2, 437).addDigit(4).addDigit(3).addDigit(7)
        assertTrue(generator.isValid)
        assertEquals(emptySet(), generator.nextDigits)
    }
}
