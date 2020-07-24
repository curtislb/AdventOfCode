package com.curtislb.adventofcode.year2019.day04.password

/**
 * A generator that produces numeric passwords whose digits are monotonically increasing from left to right.
 *
 * @param currentDigit The last digit of the current password prefix, or `null` if the current prefix is empty.
 */
class NonDecreasingGenerator private constructor(currentDigit: Int) : PasswordGenerator() {
    /**
     * A generator that produces numeric passwords whose digits are monotonically increasing from left to right.
     */
    constructor() : this(currentDigit = 0)

    override val isValid: Boolean = true

    override val nextDigits: Set<Int> = (currentDigit..9).toSet()

    override fun addDigit(digit: Int): PasswordGenerator {
        return NonDecreasingGenerator(digit)
    }
}
