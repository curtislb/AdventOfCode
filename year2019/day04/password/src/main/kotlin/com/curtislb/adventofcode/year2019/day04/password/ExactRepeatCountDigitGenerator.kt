package com.curtislb.adventofcode.year2019.day04.password

import com.curtislb.adventofcode.common.math.DECIMAL_DIGITS

/**
 * A generator that produces numeric passwords in which at least one digit appears an exact number
 * of times in sequence.
 *
 * @param repeatCount The exact number of times that a digit must be repeated in a valid password.
 * @param currentDigit The last digit of the current prefix, or `null` if the prefix is empty.
 * @param currentCount The number of times [currentDigit] repeats at the end of the current prefix.
 * @param foundRepeatCountDigit `true` if the current prefix has been found to contain a digit that
 *  appears exactly [repeatCount] times in sequence, or `false` otherwise.
 */
class ExactRepeatCountDigitGenerator private constructor(
    private val repeatCount: Int,
    private val currentDigit: Int?,
    private val currentCount: Int,
    private val foundRepeatCountDigit: Boolean
) : PasswordGenerator() {
    override val isValid: Boolean = foundRepeatCountDigit || currentCount == repeatCount

    override val nextDigits: Set<Int> = DECIMAL_DIGITS

    /**
     * A generator that produces numeric passwords in which at least one digit is repeated a
     * specific number of times in sequence.
     *
     * @param repeatCount The exact number of times a digit must be repeated in a valid password.
     */
    constructor(repeatCount: Int) : this(
        repeatCount,
        currentDigit = null,
        currentCount = 0,
        foundRepeatCountDigit = false
    )

    override fun addDigit(digit: Int): PasswordGenerator {
        return if (digit == currentDigit) {
            ExactRepeatCountDigitGenerator(
                repeatCount,
                currentDigit,
                currentCount + 1,
                foundRepeatCountDigit
            )
        } else {
            ExactRepeatCountDigitGenerator(repeatCount, digit, 1, isValid)
        }
    }
}
