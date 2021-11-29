package com.curtislb.adventofcode.common.math

/**
 * Returns the value of this integer raised to the given non-negative [exponent].
 *
 * @throws IllegalArgumentException If [exponent] is negative.
 */
fun Int.pow(exponent: Int): Int {
    require(exponent >= 0) { "Exponent must be non-negative: $exponent" }
    var product = 1
    repeat(exponent) { product *= this }
    return product
}

/**
 * Returns the value of this integer raised to the given non-negative [exponent].
 *
 * @throws IllegalArgumentException If [exponent] is negative.
 */
fun Long.pow(exponent: Int): Long {
    require(exponent >= 0L) { "Exponent must be non-negative: $exponent" }
    var product = 1L
    repeat(exponent) { product *= this }
    return product
}
