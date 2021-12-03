package com.curtislb.adventofcode.common.math

/**
 * Returns the hexadecimal digit value corresponding to this character.
 *
 * @throws IllegalArgumentException If this character has no corresponding digit.
 */
fun Char.toDigit(): Int = when (this) {
    in '0'..'9' -> this - '0'
    in 'A'..'Z' -> this - 'A' + 10
    in 'a'..'z' -> this - 'a' + 10
    else -> throw IllegalArgumentException("Invalid digit character: $this")
}

/**
 * Returns the integer given by concatenating the digits in this iterable in the given [base].
 *
 * @throws IllegalArgumentException If any number in this iterable is not a digit in the given
 *  [base].
 */
fun Iterable<Int>.digitsToInt(base: Int = 10): Int {
    val digitRange = 0 until base
    return fold(0) { prefix, digit ->
        require(digit in digitRange) { "Invalid digit for base $base: $digit" }
        prefix * base + digit
    }
}
