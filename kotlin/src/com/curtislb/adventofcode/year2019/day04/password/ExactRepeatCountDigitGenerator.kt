package com.curtislb.adventofcode.year2019.day04.password

import com.curtislb.adventofcode.common.math.allDigits

/**
 * Generates passwords containing at least one digit that is repeated exactly [repeatCount] times in sequence.
 */
class ExactRepeatCountDigitGenerator(
    private val repeatCount: Int,
    private val _currentDigit: Int? = null,
    private val _currentCount: Int = 0,
    private val _foundRepeatCountDigit: Boolean = false
) : PasswordGenerator() {
    override val isValid: Boolean = _foundRepeatCountDigit || _currentCount == repeatCount

    override val nextDigits: Set<Int> = allDigits

    override fun addDigit(digit: Int): PasswordGenerator {
        return if (digit == _currentDigit) {
            ExactRepeatCountDigitGenerator(repeatCount, _currentDigit, _currentCount + 1, _foundRepeatCountDigit)
        } else {
            ExactRepeatCountDigitGenerator(repeatCount, digit, 1, isValid)
        }
    }
}
