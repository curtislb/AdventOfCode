package com.curtislb.adventofcode.year2019.day04.password

import com.curtislb.adventofcode.common.math.decimalDigits

/**
 * A generator that produces numeric passwords in which at least one digit appears two or more times in sequence.
 *
 * @param currentDigit The last digit of the current password prefix, or null if the current prefix is empty.
 * @param foundRepeatedDigit `true` if the current password prefix has been found to contain a digit that appears two or
 *  more times in sequence, or `false` otherwise.
 */
class HasRepeatedDigitGenerator private constructor(
    private val currentDigit: Int?,
    private val foundRepeatedDigit: Boolean
) : PasswordGenerator() {
    /**
     * A generator that produces numeric passwords in which at least one digit appears two or more times in sequence.
     */
    constructor() : this(currentDigit = null, foundRepeatedDigit = false)

    override val isValid: Boolean = foundRepeatedDigit

    override val nextDigits: Set<Int> = decimalDigits

    override fun addDigit(digit: Int): PasswordGenerator {
        return HasRepeatedDigitGenerator(digit, foundRepeatedDigit || digit == currentDigit)
    }
}
