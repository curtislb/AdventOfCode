package com.curtislb.adventofcode.common.math

/**
 * Returns the value of this integer raised to the given non-negative [exponent].
 *
 * @throws IllegalArgumentException If [exponent] is negative.
 */
fun Long.pow(exponent: Int): Long {
    require(exponent >= 0) { "Exponent must be non-negative: $exponent" }
    return (1..exponent).fold(1L) { product, _ -> product * this }
}

/**
 * Returns the value of this integer raised to the given non-negative [exponent].
 *
 * @throws IllegalArgumentException If [exponent] is negative.
 */
fun Int.pow(exponent: Int): Int = toLong().pow(exponent).toInt()
