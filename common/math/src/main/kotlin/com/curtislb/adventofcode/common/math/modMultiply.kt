package com.curtislb.adventofcode.common.math

/**
 * Returns the result of multiplying this number and [multiplicand], modulo the given [modulus].
 */
fun Long.modMultiply(multiplicand: Long, modulus: Long): Long {
    return ((this % modulus) * (multiplicand % modulus)) % modulus
}
