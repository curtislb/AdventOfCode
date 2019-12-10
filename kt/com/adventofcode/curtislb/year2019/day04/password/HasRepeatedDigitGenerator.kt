package com.adventofcode.curtislb.year2019.day04.password

import com.adventofcode.curtislb.common.math.Digits

/**
 * TODO
 */
class HasRepeatedDigitGenerator(
    private val currentDigit: Int? = null,
    private val foundRepeatedDigit: Boolean = false
) : PasswordGenerator() {

    override val isValid: Boolean = foundRepeatedDigit

    override val nextDigits: Set<Int> = Digits.ALL

    override fun addDigit(digit: Int): PasswordGenerator {
        return HasRepeatedDigitGenerator(digit, foundRepeatedDigit || digit == currentDigit)
    }
}
