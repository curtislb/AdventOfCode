package com.curtislb.adventofcode.year2019.day04.password

import com.curtislb.adventofcode.common.math.decimalDigits

/**
 * A generator that produces numeric passwords whose corresponding base-10 integer values are within a given range.
 *
 * @param minValue The minimum (inclusive) integer value for a valid password.
 * @param maxValue The maximum (inclusive) integer value for a valid password.
 * @param prefix The integer value of the current password prefix.
 */
class InRangeGenerator private constructor(
    private val minValue: Int,
    private val maxValue: Int,
    private val prefix: Int
) : PasswordGenerator() {
    /**
     * A generator that produces numeric passwords whose corresponding base-10 integer values are within a given range.
     *
     * @param minValue The minimum (inclusive) integer value for a valid password.
     * @param maxValue The maximum (inclusive) integer value for a valid password.
     */
    constructor(minValue: Int, maxValue: Int) : this(minValue, maxValue, prefix = 0)

    override val isValid: Boolean = prefix in minValue..maxValue

    override val nextDigits: Set<Int> = if (prefix >= maxValue) emptySet() else decimalDigits

    override fun addDigit(digit: Int): PasswordGenerator {
        return InRangeGenerator(minValue, maxValue, prefix * 10 + digit)
    }
}
