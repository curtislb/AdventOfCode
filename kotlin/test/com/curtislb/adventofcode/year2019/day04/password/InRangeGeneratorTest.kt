package com.curtislb.adventofcode.year2019.day04.password

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
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

    @Test fun testSingleNumberRange() {
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

    @Test fun testMultipleNumberRange() {
        var generator = InRangeGenerator(0, 1).addDigit(0)
        assertTrue(generator.isValid)
        assertEquals(setOf(0, 1), generator.nextDigits)

        generator = InRangeGenerator(0, 1).addDigit(1)
        assertTrue(generator.isValid)
        assertEquals(emptySet(), generator.nextDigits)
    }
}
