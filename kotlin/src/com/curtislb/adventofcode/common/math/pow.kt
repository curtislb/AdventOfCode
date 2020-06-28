package com.curtislb.adventofcode.common.math

/**
 * Returns the value of this integer raised to the given non-negative [exponent].
 */
fun Long.pow(exponent: Int): Long {
    assert(exponent >= 0)
    return (1..exponent).fold(1L) { product, _ -> product * this }
}

/**
 * Returns the value of this integer raised to the given non-negative [exponent].
 */
fun Int.pow(exponent: Int): Int = toLong().pow(exponent).toInt()
