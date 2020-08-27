package com.curtislb.adventofcode.common.math

import java.lang.IllegalArgumentException

/**
 * Returns the digit at [position] (from left to right) for this non-negative integer in the given [base].
 *
 * The value of [position] must be in the range `0 until n`, where `n` is the number of significant (i.e. not leading
 * zero) digits of this integer in the given [base].
 *
 * @throws IllegalArgumentException If there is no digit at [position] for this number in the given [base].
 */
fun Int.bigEndianDigit(position: Int, base: Int = 10): Int {
    assert(this >= 0)
    assert(position >= 0)
    assert(base >= 2)

    // Check if position is valid for this number in the given base.
    val digitCount = countDigits(base)
    if (position >= digitCount) {
        throw IllegalArgumentException("No digit at position $position.")
    }

    return littleEndianDigit(digitCount - position - 1, base)
}

/**
 * Returns the digit at [position] (from right to left) for this non-negative integer in the given [base].
 *
 * If the value of [position] is greater than the number of significant digits in the given [base], this method will
 * return 0.
 */
fun Int.littleEndianDigit(position: Int, base: Int = 10): Int {
    assert(this >= 0)
    assert(position >= 0)
    assert(base >= 2)
    return (this / base.pow(position)) % base
}
