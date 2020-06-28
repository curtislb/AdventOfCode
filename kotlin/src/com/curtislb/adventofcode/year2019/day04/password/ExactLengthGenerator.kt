package com.curtislb.adventofcode.year2019.day04.password

import com.curtislb.adventofcode.common.math.decimalDigits


/**
 * A generator that produces numeric passwords with a given number of digits.
 *
 * @param length The number of digits (including leading zeros) that a valid password must contain.
 * @param currentLength The number of digits (including leading zeros) of the current password prefix.
 */
class ExactLengthGenerator private constructor(
    private val length: Int,
    private val currentLength: Int
) : PasswordGenerator() {
    /**
     * A generator that produces numeric passwords with a given number of digits.
     *
     * @param length The number of digits (including leading zeros) that a valid password must contain.
     */
    constructor(length: Int) : this(length, currentLength = 0)

    override val isValid: Boolean = currentLength == length

    override val nextDigits: Set<Int> = if (currentLength >= length) emptySet() else decimalDigits

    override fun addDigit(digit: Int): PasswordGenerator = ExactLengthGenerator(length, currentLength + 1)
}
