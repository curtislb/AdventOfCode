package com.curtislb.adventofcode.common.math

/**
 * Returns the numerical digit corresponding to this character.
 *
 * @throws IllegalArgumentException If this character has no corresponding digit.
 */
fun Char.toDigit(): Int = when (this) {
    in '0'..'9' -> this - '0'
    in 'A'..'Z' -> this - 'A' + 10
    in 'a'..'z' -> this - 'a' + 10
    else -> throw IllegalArgumentException("'$this' can't be converted to a digit.")
}
