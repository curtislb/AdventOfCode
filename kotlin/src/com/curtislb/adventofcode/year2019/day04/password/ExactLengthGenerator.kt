package com.curtislb.adventofcode.year2019.day04.password

import com.curtislb.adventofcode.common.math.allDigits

/**
 * Generates passwords with a number of digits equal to [length].
 */
class ExactLengthGenerator(private val length: Int, private val _currentLength: Int = 0) : PasswordGenerator() {
    override val isValid: Boolean = _currentLength == length

    override val nextDigits: Set<Int> = if (_currentLength >= length) emptySet() else allDigits

    override fun addDigit(digit: Int): PasswordGenerator = ExactLengthGenerator(length, _currentLength + 1)
}
