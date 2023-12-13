package com.curtislb.adventofcode.common.number

/**
 * Returns the value of this integer raised to the given non-negative [exponent].
 *
 * @throws IllegalArgumentException If [exponent] is negative.
 */
fun Int.pow(exponent: Int): Int {
    require(exponent >= 0) { "Exponent must be non-negative: $exponent" }
    return when (this) {
        1 -> 1
        -1 -> if (exponent % 2 == 0) 1 else -1
        else -> {
            var product = 1
            repeat(exponent) { product *= this }
            product
        }
    }
}

/**
 * Returns the value of this integer raised to the given non-negative [exponent].
 *
 * @throws IllegalArgumentException If [exponent] is negative.
 */
fun Long.pow(exponent: Int): Long {
    require(exponent >= 0L) { "Exponent must be non-negative: $exponent" }
    return when (this) {
        1L -> 1L
        -1L -> if (exponent % 2L == 0L) 1L else -1L
        else -> {
            var product = 1L
            repeat(exponent) { product *= this }
            product
        }
    }
}
