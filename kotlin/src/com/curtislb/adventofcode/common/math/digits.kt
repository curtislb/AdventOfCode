package com.curtislb.adventofcode.common.math

/**
 * A set containing all base-10 digits.
 */
val allDigits: Set<Int> = (0..9).toSet()

/**
 * Returns the value of the digit at a given [position] when this integer is written in the given [base].
 */
fun Int.digit(position: Int, base: Int = 10): Int = (this / base.pow(position)) % base

/**
 * Returns base-10 digit corresponding to this character.
 *
 * @throws IllegalArgumentException If this character has no corresponding base-10 digit.
 */
fun Char.toDigit(): Int {
    return when (this) {
        in '0'..'9' -> this - '0'
        else -> throw IllegalArgumentException("'$this' can't be converted to a digit.")
    }
}
