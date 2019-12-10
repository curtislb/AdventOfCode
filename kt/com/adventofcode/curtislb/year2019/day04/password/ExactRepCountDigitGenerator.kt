package com.adventofcode.curtislb.year2019.day04.password

import com.adventofcode.curtislb.common.math.Digits

/**
 * TODO
 */
class ExactRepCountDigitGenerator(
    private val repCount: Int,
    private val currentDigit: Int? = null,
    private val currentCount: Int = 0,
    private val foundRepCountDigit: Boolean = false
) : PasswordGenerator() {

    override val isValid: Boolean = foundRepCountDigit || currentCount == repCount

    override val nextDigits: Set<Int> = Digits.ALL

    override fun addDigit(digit: Int): PasswordGenerator {
        return if (digit == currentDigit) {
            ExactRepCountDigitGenerator(repCount, currentDigit, currentCount + 1, foundRepCountDigit)
        } else {
            ExactRepCountDigitGenerator(repCount, digit, 1, isValid)
        }
    }
}
