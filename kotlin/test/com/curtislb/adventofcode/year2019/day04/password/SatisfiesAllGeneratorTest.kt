package com.curtislb.adventofcode.year2019.day04.password

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Tests [SatisfiesAllGenerator].
 */
class SatisfiesAllGeneratorTest {
    @Test fun testWithNoGenerators() {
        val generator = SatisfiesAllGenerator()
        assertTrue(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generator.nextDigits)
        for (digit in 0..9) {
            val generatorWithDigit = generator.addDigit(digit)
            assertTrue(generatorWithDigit.isValid)
            assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), generatorWithDigit.nextDigits)
        }
    }

    @Test fun testWithOneGenerator() {
        val fakeGenerator = FakeGenerator()
        var generator: PasswordGenerator = SatisfiesAllGenerator(fakeGenerator)
        fakeGenerator.isValidAnswer = true
        fakeGenerator.nextDigitsAnswer = setOf(2, 3, 5)
        assertTrue(generator.isValid)
        assertEquals(setOf(2, 3, 5), generator.nextDigits)

        generator = generator.addDigit(2)
        fakeGenerator.isValidAnswer = false
        fakeGenerator.nextDigitsAnswer = setOf(0, 1, 2, 3, 4, 5, 9)
        assertFalse(generator.isValid)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 9), generator.nextDigits)
    }

    @Test fun testWithTwoGenerators() {
        val fakeGeneratorA = FakeGenerator()
        val fakeGeneratorB = FakeGenerator()
        var generator: PasswordGenerator = SatisfiesAllGenerator(fakeGeneratorA, fakeGeneratorB)
        fakeGeneratorA.isValidAnswer = true
        fakeGeneratorB.isValidAnswer = true
        fakeGeneratorA.nextDigitsAnswer = setOf(0, 1, 2, 3, 4, 5, 6)
        fakeGeneratorB.nextDigitsAnswer = setOf(3, 4, 5, 6, 7, 8, 9)
        assertTrue(generator.isValid)
        assertEquals(setOf(3, 4, 5, 6), generator.nextDigits)

        generator = generator.addDigit(3)
        fakeGeneratorA.isValidAnswer = true
        fakeGeneratorB.isValidAnswer = false
        fakeGeneratorA.nextDigitsAnswer = setOf(0, 1, 2, 3, 4)
        fakeGeneratorB.nextDigitsAnswer = setOf(0, 2, 4, 6, 8)
        assertFalse(generator.isValid)
        assertEquals(setOf(0, 2, 4), generator.nextDigits)

        generator = generator.addDigit(2)
        fakeGeneratorA.isValidAnswer = false
        fakeGeneratorB.isValidAnswer = true
        fakeGeneratorA.nextDigitsAnswer = setOf(1, 3, 5, 7, 9)
        fakeGeneratorB.nextDigitsAnswer = setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        assertFalse(generator.isValid)
        assertEquals(setOf(1, 3, 5, 7, 9), generator.nextDigits)

        generator = generator.addDigit(9)
        fakeGeneratorA.isValidAnswer = false
        fakeGeneratorB.isValidAnswer = false
        fakeGeneratorA.nextDigitsAnswer = setOf(1, 2, 3, 4, 5, 6, 7, 8)
        fakeGeneratorB.nextDigitsAnswer = emptySet()
        assertFalse(generator.isValid)
        assertEquals(emptySet(), generator.nextDigits)
    }

    companion object {
        /**
         * A fake password generator that allows custom answers to be set for testing.
         */
        private class FakeGenerator : PasswordGenerator() {
            /**
             * The value that should be returned for [isValid].
             */
            var isValidAnswer: Boolean? = null

            /**
             * The value that should be returned for [nextDigits].
             */
            var nextDigitsAnswer: Set<Int>? = null

            override val isValid: Boolean
                get() = isValidAnswer ?: throw IllegalStateException("No answer provided for isValid.")

            override val nextDigits: Set<Int>
                get() = nextDigitsAnswer ?: throw IllegalStateException("No answer provided for nextDigits.")

            override fun addDigit(digit: Int): PasswordGenerator = this
        }
    }
}
