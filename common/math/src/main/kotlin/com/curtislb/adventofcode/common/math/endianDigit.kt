package com.curtislb.adventofcode.common.math

/**
 * Returns the digit at [position] (from left) for this non-negative integer in the given [base].
 *
 * The value of [position] must be in the range `0 until n`, where `n` is the number of significant
 * (i.e. not leading zero) digits of this integer in the given [base].
 *
 * @throws IllegalArgumentException If this number is negative, [position] is negative, [base] is
 *  less than 2, or there is no digit at [position] for this number in the given [base].
 */
fun Int.bigEndianDigit(position: Int, base: Int = 10): Int {
    require(this >= 0) { "Integer must be non-negative: $this" }
    require(position >= 0) { "Position must be non-negative: $position" }
    require(base >= 2) { "Base must be at least 2: $base" }

    // Check if position is valid for this number in the given base.
    val digitCount = countDigits(base)
    require(position < digitCount) {
        "Integer $this has no big-endian digit at position $position in base $base."
    }

    return littleEndianDigitInternal(digitCount - position - 1, base)
}

/**
 * Returns the digit at [position] (from right) for this non-negative integer in the given [base].
 *
 * If the value of [position] is greater than the number of significant digits in the given [base],
 * this method will return 0.
 *
 * @throws IllegalArgumentException If this number is negative, [position] is negative, or [base] is
 *  less than 2.
 */
fun Int.littleEndianDigit(position: Int, base: Int = 10): Int {
    require(this >= 0) { "Integer must be non-negative: $this" }
    require(position >= 0) { "Position must be non-negative: $position" }
    require(base >= 2) { "Base must be at least 2: $base" }
    return littleEndianDigitInternal(position, base)
}

/**
 * Returns the digit at [position] (from right) for this non-negative integer in the given [base].
 */
private fun Int.littleEndianDigitInternal(position: Int, base: Int): Int =
    (this / base.pow(position)) % base
