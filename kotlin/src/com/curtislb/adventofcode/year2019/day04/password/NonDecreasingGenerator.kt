package com.curtislb.adventofcode.year2019.day04.password

/**
 * A generator that produces numeric passwords whose digits are monotonically increasing from left to right.
 *
 * @param currentDigit The last digit of the current password prefix, or `null` if the current prefix is empty.
 * @param isNonDecreasing `true` if the current password prefix is monotonically increasing, or `false` otherwise.
 */
class NonDecreasingGenerator private constructor(
    private val currentDigit: Int,
    private val isNonDecreasing: Boolean
) : PasswordGenerator() {
    /**
     * A generator that produces numeric passwords whose digits are monotonically increasing from left to right.
     */
    constructor() : this(currentDigit = 0, isNonDecreasing = true)

    override val isValid: Boolean = isNonDecreasing

    override val nextDigits: Set<Int> = if (isNonDecreasing) (currentDigit..9).toSet() else emptySet()

    override fun addDigit(digit: Int): PasswordGenerator {
        return NonDecreasingGenerator(digit, isNonDecreasing && digit >= currentDigit)
    }
}
