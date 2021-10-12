package com.curtislb.adventofcode.year2019.day04.password

import com.curtislb.adventofcode.common.math.DECIMAL_DIGITS

/**
 * A generator that produces numeric passwords whose corresponding base-10 integer values are within
 * a given range.
 *
 * @param minValue The minimum (inclusive) non-negative integer value for a valid password.
 * @param maxValue The maximum (inclusive) non-negative integer value for a valid password.
 * @param prefix The integer value of the current password prefix.
 *
 * @throws IllegalArgumentException If [minValue] or [maxValue] is negative.
 */
class InRangeGenerator private constructor(
    private val minValue: Int,
    private val maxValue: Int,
    private val prefix: Int
) : PasswordGenerator() {
    init {
        require(minValue >= 0) { "Minimum value must be non-negative: $minValue" }
        require(maxValue >= 0) { "Maximum value must be non-negative: $maxValue" }
    }

    override val isValid: Boolean = prefix in minValue..maxValue

    override val nextDigits: Set<Int> = if (minValue > maxValue) {
        emptySet()
    } else {
        DECIMAL_DIGITS.filter { prefix * 10 + it <= maxValue }.toSet()
    }

    /**
     * A generator that produces numeric passwords whose corresponding base-10 integer values are
     * within a given range.
     *
     * @param minValue The minimum (inclusive) non-negative integer value for a valid password.
     * @param maxValue The maximum (inclusive) non-negative integer value for a valid password.
     *
     * @throws IllegalArgumentException If [minValue] or [maxValue] is negative.
     */
    constructor(minValue: Int, maxValue: Int) : this(minValue, maxValue, prefix = 0)

    override fun addDigit(digit: Int): PasswordGenerator {
        return InRangeGenerator(minValue, maxValue, prefix * 10 + digit)
    }
}
