package com.curtislb.adventofcode.year2019.day04.password

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Tests [NonDecreasingGenerator].
 */
class NonDecreasingGeneratorTest {
    @Test
    fun testWithEmptyGenerator() {
        val generator = NonDecreasingGenerator()
        assertTrue(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)
    }

    @Test
    fun testWithSingleDigit() {
        for (digit in 0..9) {
            val generator = NonDecreasingGenerator().addDigit(digit)
            assertTrue(generator.isValid)
            assertEquals((0..9).filter { it >= digit }.toSet(), generator.nextDigits)
        }
    }

    @Test
    fun testWithIncreasingDigits() {
        var generator: PasswordGenerator = NonDecreasingGenerator().addDigit(0).addDigit(1)
        assertTrue(generator.isValid)
        assertEquals(setOf(1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(1).addDigit(2)
        assertTrue(generator.isValid)
        assertEquals(setOf(2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(3).addDigit(5)
        assertTrue(generator.isValid)
        assertEquals(setOf(5, 6, 7, 8, 9), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(8).addDigit(9)
        assertTrue(generator.isValid)
        assertEquals(setOf(9), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(0).addDigit(9)
        assertTrue(generator.isValid)
        assertEquals(setOf(9), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(0).addDigit(1).addDigit(2)
        assertTrue(generator.isValid)
        assertEquals(setOf(2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(2).addDigit(6).addDigit(7)
        assertTrue(generator.isValid)
        assertEquals(setOf(7, 8, 9), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(7).addDigit(8).addDigit(9)
        assertTrue(generator.isValid)
        assertEquals(setOf(9), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(0).addDigit(1).addDigit(2).addDigit(3)
        assertTrue(generator.isValid)
        assertEquals(setOf(3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(2).addDigit(4).addDigit(7).addDigit(8)
        assertTrue(generator.isValid)
        assertEquals(setOf(8, 9), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(6).addDigit(7).addDigit(8).addDigit(9)
        assertTrue(generator.isValid)
        assertEquals(setOf(9), generator.nextDigits)
    }

    @Test
    fun testWithRepeatedDigit() {
        var generator: PasswordGenerator = NonDecreasingGenerator().addDigit(0).addDigit(0)
        assertTrue(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(4).addDigit(4)
        assertTrue(generator.isValid)
        assertEquals(setOf(4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(9).addDigit(9)
        assertTrue(generator.isValid)
        assertEquals(setOf(9), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(0).addDigit(0).addDigit(0)
        assertTrue(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(3).addDigit(3).addDigit(3)
        assertTrue(generator.isValid)
        assertEquals(setOf(3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(9).addDigit(9).addDigit(9)
        assertTrue(generator.isValid)
        assertEquals(setOf(9), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(0).addDigit(0).addDigit(0).addDigit(0)
        assertTrue(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(6).addDigit(6).addDigit(6).addDigit(6)
        assertTrue(generator.isValid)
        assertEquals(setOf(6, 7, 8, 9), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(9).addDigit(9).addDigit(9).addDigit(9)
        assertTrue(generator.isValid)
        assertEquals(setOf(9), generator.nextDigits)
    }

    @Test
    fun testWithNonDecreasingDigits() {
        var generator: PasswordGenerator = NonDecreasingGenerator().addDigit(0).addDigit(0).addDigit(1)
        assertTrue(generator.isValid)
        assertEquals(setOf(1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(1).addDigit(1).addDigit(2)
        assertTrue(generator.isValid)
        assertEquals(setOf(2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(2).addDigit(2).addDigit(2).addDigit(3)
        assertTrue(generator.isValid)
        assertEquals(setOf(3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(5).addDigit(7).addDigit(7).addDigit(7)
        assertTrue(generator.isValid)
        assertEquals(setOf(7, 8, 9), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(4).addDigit(5).addDigit(5).addDigit(8)
        assertTrue(generator.isValid)
        assertEquals(setOf(8, 9), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(2).addDigit(4).addDigit(4).addDigit(5).addDigit(6)
        assertTrue(generator.isValid)
        assertEquals(setOf(6, 7, 8, 9), generator.nextDigits)
    }

    @Test
    fun testWithNonIncreasingDigits() {
        var generator: PasswordGenerator = NonDecreasingGenerator().addDigit(1).addDigit(0).addDigit(0)
        assertFalse(generator.isValid)
        assertEquals(emptySet(), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(2).addDigit(1).addDigit(1)
        assertFalse(generator.isValid)
        assertEquals(emptySet(), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(3).addDigit(2).addDigit(2).addDigit(2)
        assertFalse(generator.isValid)
        assertEquals(emptySet(), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(7).addDigit(7).addDigit(7).addDigit(5)
        assertFalse(generator.isValid)
        assertEquals(emptySet(), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(8).addDigit(5).addDigit(5).addDigit(4)
        assertFalse(generator.isValid)
        assertEquals(emptySet(), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(6).addDigit(5).addDigit(4).addDigit(4).addDigit(2)
        assertFalse(generator.isValid)
        assertEquals(emptySet(), generator.nextDigits)
    }

    @Test
    fun testWithUnorderedDigits() {
        var generator: PasswordGenerator = NonDecreasingGenerator().addDigit(0).addDigit(2).addDigit(1)
        assertFalse(generator.isValid)
        assertEquals(emptySet(), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(9).addDigit(7).addDigit(8)
        assertFalse(generator.isValid)
        assertEquals(emptySet(), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(1).addDigit(5).addDigit(4)
        assertFalse(generator.isValid)
        assertEquals(emptySet(), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(3).addDigit(2).addDigit(6)
        assertFalse(generator.isValid)
        assertEquals(emptySet(), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(4).addDigit(6).addDigit(6).addDigit(0)
        assertFalse(generator.isValid)
        assertEquals(emptySet(), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(9).addDigit(0).addDigit(9).addDigit(7)
        assertFalse(generator.isValid)
        assertEquals(emptySet(), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(1).addDigit(3).addDigit(6).addDigit(4)
        assertFalse(generator.isValid)
        assertEquals(emptySet(), generator.nextDigits)

        generator = NonDecreasingGenerator().addDigit(8).addDigit(6).addDigit(8).addDigit(2).addDigit(2)
        assertFalse(generator.isValid)
        assertEquals(emptySet(), generator.nextDigits)
    }
}
