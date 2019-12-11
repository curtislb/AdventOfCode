package com.adventofcode.curtislb.year2019.day04.password

import com.adventofcode.curtislb.common.math.allDigits

/**
 * Generates passwords with an exact number of digits.
 * @param length The number of digits required to be a valid password.
 */
class ExactLengthGenerator(private val length: Int, private val currentLength: Int = 0) : PasswordGenerator() {
    override val isValid: Boolean = currentLength == length

    override val nextDigits: Set<Int> = if (currentLength >= length) emptySet() else allDigits

    override fun addDigit(digit: Int): PasswordGenerator = ExactLengthGenerator(length, currentLength + 1)
}
