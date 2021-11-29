package com.curtislb.adventofcode.common.math

/**
 * A set containing all base-10 digits.
 */
val DECIMAL_DIGITS = (0..9).toSet()

/**
 * Returns the hexadecimal digit corresponding to this character.
 *
 * @throws IllegalArgumentException If this character has no corresponding digit.
 */
fun Char.toDigit(): Int = when (this) {
    in '0'..'9' -> this - '0'
    in 'A'..'Z' -> this - 'A' + 10
    in 'a'..'z' -> this - 'a' + 10
    else -> throw IllegalArgumentException("Invalid digit character: $this")
}
