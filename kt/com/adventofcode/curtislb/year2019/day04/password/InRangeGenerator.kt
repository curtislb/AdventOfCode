package com.adventofcode.curtislb.year2019.day04.password

import com.adventofcode.curtislb.common.math.Digits

/**
 * TODO
 */
class InRangeGenerator(
    private val minValue: Int,
    private val maxValue: Int,
    private val prefix: Int = 0
) : PasswordGenerator() {

    override val isValid: Boolean = prefix in minValue..maxValue

    override val nextDigits: Set<Int> = if (prefix >= maxValue) Digits.NONE else Digits.ALL

    override fun addDigit(digit: Int): PasswordGenerator {
        return InRangeGenerator(minValue, maxValue, prefix * 10 + digit)
    }
}
