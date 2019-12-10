package com.adventofcode.curtislb.year2019.day04.password

import com.adventofcode.curtislb.common.math.Digits

/**
 * TODO
 */
class ExactLengthGenerator(private val length: Int, private val currentLength: Int = 0) : PasswordGenerator() {
    override val isValid: Boolean = currentLength == length

    override val nextDigits: Set<Int> = if (currentLength >= length) Digits.NONE else Digits.ALL

    override fun addDigit(digit: Int): PasswordGenerator = ExactLengthGenerator(length, currentLength + 1)
}
