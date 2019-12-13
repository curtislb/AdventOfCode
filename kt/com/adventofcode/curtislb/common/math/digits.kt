package com.adventofcode.curtislb.common.math

/**
 * A set containing all base-10 digits.
 */
val allDigits: Set<Int> = (0..9).toSet()

/**
 * Produces the value of the specified digit when this integer is written in a given base.
 * @receiver The integer to be represented in the given [base].
 * @param position The 0-indexed position (from low to high order) of the desired digit.
 * @return The value of the digit at [position] when [this] is written in base [base].
 */
fun Int.digit(position: Int, base: Int = 10): Int = (this / base.pow(position)) % base
