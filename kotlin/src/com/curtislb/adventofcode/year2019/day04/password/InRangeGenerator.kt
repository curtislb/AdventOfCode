package com.curtislb.adventofcode.year2019.day04.password

import com.curtislb.adventofcode.common.math.DECIMAL_DIGITS
import com.curtislb.adventofcode.common.math.pow

/**
 * A generator that produces numeric passwords whose corresponding base-10 integer values are within a given range.
 *
 * @param minValue The minimum (inclusive) non-negative integer value for a valid password.
 * @param maxValue The maximum (inclusive) non-negative integer value for a valid password.
 * @param prefix The integer value of the current password prefix.
 */
class InRangeGenerator private constructor(
    private val minValue: Int,
    private val maxValue: Int,
    private val prefix: Int
) : PasswordGenerator() {
    init {
        assert(minValue >= 0)
        assert(maxValue >= 0)
    }

    /**
     * A generator that produces numeric passwords whose corresponding base-10 integer values are within a given range.
     *
     * @param minValue The minimum (inclusive) non-negative integer value for a valid password.
     * @param maxValue The maximum (inclusive) non-negative integer value for a valid password.
     */
    constructor(minValue: Int, maxValue: Int) : this(minValue, maxValue, prefix = 0)

    override val isValid: Boolean = prefix in minValue..maxValue

    override val nextDigits: Set<Int> = when {
        minValue > maxValue || prefix > maxValue -> emptySet()
        else -> DECIMAL_DIGITS.filter { prefix * 10 + it <= maxValue }.toSet()
    }

    override fun addDigit(digit: Int): PasswordGenerator {
        return InRangeGenerator(minValue, maxValue, prefix * 10 + digit)
    }

    companion object {
        private fun truncateForPrefix(value: Int, prefix: Int): Int {
            if (prefix == 0) {
                return value
            }

            val valueLength = value.toString().length
            val prefixLength = prefix.toString().length
            return value % (10.pow(valueLength - prefixLength))
        }
    }
}
