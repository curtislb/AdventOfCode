package com.adventofcode.curtislb.year2019.day04.password

import com.adventofcode.curtislb.common.math.allDigits

/**
 * Generates passwords containing at least one digit that is repeated an exact number of times in sequence.
 * @param repCount The exact number of times that a digit must repeat in sequence for a password to be valid.
 */
class ExactRepCountDigitGenerator(
    private val repCount: Int,
    private val currentDigit: Int? = null,
    private val currentCount: Int = 0,
    private val foundRepCountDigit: Boolean = false
) : PasswordGenerator() {

    override val isValid: Boolean = foundRepCountDigit || currentCount == repCount

    override val nextDigits: Set<Int> = allDigits

    override fun addDigit(digit: Int): PasswordGenerator {
        return if (digit == currentDigit) {
            ExactRepCountDigitGenerator(repCount, currentDigit, currentCount + 1, foundRepCountDigit)
        } else {
            ExactRepCountDigitGenerator(repCount, digit, 1, isValid)
        }
    }
}
