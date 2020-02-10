package com.curtislb.adventofcode.year2019.day04.password

import com.curtislb.adventofcode.common.math.allDigits

/**
 * Generates passwords containing at least one digit that is repeated exactly [repCount] times in sequence.
 */
class ExactRepCountDigitGenerator(
    private val repCount: Int,
    private val _currentDigit: Int? = null,
    private val _currentCount: Int = 0,
    private val _foundRepCountDigit: Boolean = false
) : PasswordGenerator() {

    override val isValid: Boolean = _foundRepCountDigit || _currentCount == repCount

    override val nextDigits: Set<Int> = allDigits

    override fun addDigit(digit: Int): PasswordGenerator {
        return if (digit == _currentDigit) {
            ExactRepCountDigitGenerator(repCount, _currentDigit, _currentCount + 1, _foundRepCountDigit)
        } else {
            ExactRepCountDigitGenerator(repCount, digit, 1, isValid)
        }
    }
}
