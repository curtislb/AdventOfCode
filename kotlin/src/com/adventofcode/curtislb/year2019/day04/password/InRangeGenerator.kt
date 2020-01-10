package com.adventofcode.curtislb.year2019.day04.password

import com.adventofcode.curtislb.common.math.allDigits

/**
 * Generates passwords whose numeric value is within a given range.
 * @param minValue The minimum numeric value (inclusive) of a valid password.
 * @param maxValue The maximum numeric value (inclusive) of a valid password.
 */
class InRangeGenerator(
    private val minValue: Int,
    private val maxValue: Int,
    private val prefix: Int = 0
) : PasswordGenerator() {

    override val isValid: Boolean = prefix in minValue..maxValue

    override val nextDigits: Set<Int> = if (prefix >= maxValue) emptySet() else allDigits

    override fun addDigit(digit: Int): PasswordGenerator {
        return InRangeGenerator(minValue, maxValue, prefix * 10 + digit)
    }
}
