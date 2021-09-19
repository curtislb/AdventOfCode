package com.curtislb.adventofcode.year2019.day04.password

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Tests [ExactRepeatCountDigitGenerator].
 */
class ExactRepeatCountDigitGeneratorTest {
    @Test
    fun testWithRepeatCountZero() {
        val generator = ExactRepeatCountDigitGenerator(0)
        assertTrue(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        val generatorWithPrefix1 = generator.addDigit(1)
        assertTrue(generatorWithPrefix1.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generatorWithPrefix1.nextDigits)

        val generatorWithPrefix11 = generatorWithPrefix1.addDigit(1)
        assertTrue(generatorWithPrefix11.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generatorWithPrefix11.nextDigits)
    }

    @Test
    fun testWithRepeatCountOne() {
        val generator = ExactRepeatCountDigitGenerator(1)
        assertFalse(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        val generatorWithPrefix2 = generator.addDigit(2)
        assertTrue(generatorWithPrefix2.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generatorWithPrefix2.nextDigits)

        val generatorWithPrefix22 = generatorWithPrefix2.addDigit(2)
        assertFalse(generatorWithPrefix22.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generatorWithPrefix22.nextDigits)

        val generatorWithPrefix223 = generatorWithPrefix22.addDigit(3)
        assertTrue(generatorWithPrefix223.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generatorWithPrefix223.nextDigits)

        val generatorWithPrefix22344 = generatorWithPrefix223.addDigit(4).addDigit(4)
        assertTrue(generatorWithPrefix22344.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generatorWithPrefix22344.nextDigits)
    }

    @Test
    fun testWithRepeatCountTwo() {
        val generator = ExactRepeatCountDigitGenerator(2)
        assertFalse(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)

        val generatorWithPrefix5 = generator.addDigit(5)
        assertFalse(generatorWithPrefix5.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generatorWithPrefix5.nextDigits)

        val generatorWithPrefix55 = generatorWithPrefix5.addDigit(5)
        assertTrue(generatorWithPrefix55.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generatorWithPrefix55.nextDigits)

        val generatorWithPrefix555 = generatorWithPrefix55.addDigit(5)
        assertFalse(generatorWithPrefix555.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generatorWithPrefix555.nextDigits)

        val generatorWithPrefix5556 = generatorWithPrefix555.addDigit(6)
        assertFalse(generatorWithPrefix5556.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generatorWithPrefix5556.nextDigits)

        val generatorWithPrefix55566 = generatorWithPrefix5556.addDigit(6)
        assertTrue(generatorWithPrefix55566.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generatorWithPrefix55566.nextDigits)

        val generatorWithPrefix555667 = generatorWithPrefix55566.addDigit(7)
        assertTrue(generatorWithPrefix555667.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generatorWithPrefix555667.nextDigits)

        val generatorWithPrefix5556677 = generatorWithPrefix555667.addDigit(7)
        assertTrue(generatorWithPrefix5556677.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generatorWithPrefix5556677.nextDigits)
    }
}
