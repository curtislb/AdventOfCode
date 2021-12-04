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
 * Returns the integer given by concatenating the digits in this iterable with the given [radix].
 *
 * @throws IllegalArgumentException If any number in this iterable is not a digit in the given
 *  [radix].
 */
fun Iterable<Int>.digitsToInt(radix: Int = 10): Int {
    val digitRange = 0 until radix
    return fold(0) { prefix, digit ->
        require(digit in digitRange) { "Invalid digit for base $radix: $digit" }
        prefix * radix + digit
    }
}
