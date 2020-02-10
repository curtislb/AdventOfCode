package com.curtislb.adventofcode.year2019.day04.password

import com.curtislb.adventofcode.common.math.allDigits

/**
 * Generates passwords whose numeric value is within the range [minValue]..[maxValue].
 */
class InRangeGenerator(
    private val minValue: Int,
    private val maxValue: Int,
    private val _prefix: Int = 0
) : PasswordGenerator() {

    override val isValid: Boolean = _prefix in minValue..maxValue

    override val nextDigits: Set<Int> = if (_prefix >= maxValue) emptySet() else allDigits

    override fun addDigit(digit: Int): PasswordGenerator {
        return InRangeGenerator(minValue, maxValue, _prefix * 10 + digit)
    }
}
