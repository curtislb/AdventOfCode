package com.curtislb.adventofcode.year2019.day04.password

import com.curtislb.adventofcode.common.testing.assertContainsExactly
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test

/**
 * Tests [ExactLengthGenerator].
 */
class ExactLengthGeneratorTest {
    @Test
    fun testWithLengthZero() {
        val generator = ExactLengthGenerator(0)
        assertTrue(generator.isValid)
        assertEquals(emptySet(), generator.nextDigits)
        for (digit in 0..9) {
            assertFalse(generator.addDigit(digit).isValid)
        }
    }

    @Test
    fun testWithLengthOne() {
        val generator = ExactLengthGenerator(1)
        assertFalse(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)
        for (digit in 0..9) {
            val generatorWithDigit = generator.addDigit(digit)
            assertTrue(generatorWithDigit.isValid)
            assertEquals(emptySet(), generatorWithDigit.nextDigits)
            for (extraDigit in 0..9) {
                assertFalse(generatorWithDigit.addDigit(extraDigit).isValid)
            }
        }
    }

    @Test
    fun testWithLengthTwo() {
        val generator = ExactLengthGenerator(2)
        assertFalse(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)
        for (firstDigit in 0..9) {
            val generatorWithDigit = generator.addDigit(firstDigit)
            assertFalse(generatorWithDigit.isValid)
            assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generatorWithDigit.nextDigits)
            for (secondDigit in 0..9) {
                val generatorWithTwoDigits = generatorWithDigit.addDigit(secondDigit)
                assertTrue(generatorWithTwoDigits.isValid)
                assertEquals(emptySet(), generatorWithTwoDigits.nextDigits)
                for (extraDigit in 0..9) {
                    assertFalse(generatorWithTwoDigits.addDigit(extraDigit).isValid)
                }
            }
        }
    }

    @Test
    fun testCountPasswords() {
        assertEquals(1, ExactLengthGenerator(0).countPasswords())
        assertEquals(0, ExactLengthGenerator(0).addDigit(0).countPasswords())
        assertEquals(10, ExactLengthGenerator(1).countPasswords())
        assertEquals(1, ExactLengthGenerator(1).addDigit(1).countPasswords())
        assertEquals(0, ExactLengthGenerator(1).addDigit(2).addDigit(3).countPasswords())
        assertEquals(100, ExactLengthGenerator(2).countPasswords())
        assertEquals(10, ExactLengthGenerator(2).addDigit(4).countPasswords())
        assertEquals(1, ExactLengthGenerator(2).addDigit(5).addDigit(6).countPasswords())
        assertEquals(
            0,
            ExactLengthGenerator(2).addDigit(7).addDigit(8).addDigit(9).countPasswords()
        )
        assertEquals(100000, ExactLengthGenerator(5).countPasswords())
        assertEquals(10000, ExactLengthGenerator(6).addDigit(6).addDigit(3).countPasswords())
    }

    @Test
    fun testGeneratePasswordSuffixes() {
        assertEquals(listOf(""), ExactLengthGenerator(0).generatePasswordSuffixes().toList())
        assertEquals(
            emptyList(),
            ExactLengthGenerator(0).addDigit(0).generatePasswordSuffixes().toList()
        )
        assertContainsExactly(
            listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"),
            ExactLengthGenerator(1).generatePasswordSuffixes().toList()
        )
        assertEquals(
            listOf(""),
            ExactLengthGenerator(1).addDigit(1).generatePasswordSuffixes().toList()
        )
        assertEquals(
            emptyList(),
            ExactLengthGenerator(1).addDigit(2).addDigit(3).generatePasswordSuffixes().toList()
        )
        assertContainsExactly(
            (0..99).map { it.toString().padStart(2, '0') },
            ExactLengthGenerator(2).generatePasswordSuffixes().toList()
        )
        assertContainsExactly(
            listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"),
            ExactLengthGenerator(2).addDigit(4).generatePasswordSuffixes().toList()
        )
        assertEquals(
            listOf(""),
            ExactLengthGenerator(2).addDigit(5).addDigit(6).generatePasswordSuffixes().toList()
        )
        assertEquals(
            emptyList(),
            ExactLengthGenerator(2).addDigit(7).addDigit(8).addDigit(9).generatePasswordSuffixes()
                .toList()
        )
        assertEquals(100000, ExactLengthGenerator(5).generatePasswordSuffixes().count())
        assertEquals(
            10000,
            ExactLengthGenerator(6).addDigit(9).addDigit(7).generatePasswordSuffixes().count()
        )
    }
}
