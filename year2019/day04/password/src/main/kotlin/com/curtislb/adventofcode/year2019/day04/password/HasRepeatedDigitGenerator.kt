package com.curtislb.adventofcode.year2019.day04.password

import com.curtislb.adventofcode.common.math.DECIMAL_DIGITS

/**
 * A generator that produces numeric passwords in which at least one digit appears two or more times
 * in sequence.
 *
 * @param currentDigit The last digit of the current prefix, or `null` if the prefix is empty.
 * @param hasRepeatedDigit `true` if the current prefix contains a digit that appears two or more
 *  times in sequence, or `false` otherwise.
 */
class HasRepeatedDigitGenerator private constructor(
    private val currentDigit: Int?,
    private val hasRepeatedDigit: Boolean
) : PasswordGenerator() {
    override val isValid: Boolean = hasRepeatedDigit

    override val nextDigits: Set<Int> = DECIMAL_DIGITS

    /**
     * A generator that produces numeric passwords in which at least one digit appears two or more
     * times in sequence.
     */
    constructor() : this(currentDigit = null, hasRepeatedDigit = false)

    override fun addDigit(digit: Int): PasswordGenerator {
        return HasRepeatedDigitGenerator(digit, hasRepeatedDigit || digit == currentDigit)
    }
}
