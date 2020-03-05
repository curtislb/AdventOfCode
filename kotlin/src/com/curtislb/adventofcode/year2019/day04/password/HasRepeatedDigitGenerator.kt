package com.curtislb.adventofcode.year2019.day04.password

import com.curtislb.adventofcode.common.math.allDigits

/**
 * Generates passwords containing at least one digit that appears two or more times in sequence.
 */
class HasRepeatedDigitGenerator(
    private val _currentDigit: Int? = null,
    private val _foundRepeatedDigit: Boolean = false
) : PasswordGenerator() {
    override val isValid: Boolean = _foundRepeatedDigit

    override val nextDigits: Set<Int> = allDigits

    override fun addDigit(digit: Int): PasswordGenerator {
        return HasRepeatedDigitGenerator(digit, _foundRepeatedDigit || digit == _currentDigit)
    }
}
