package com.curtislb.adventofcode.common.number

import java.math.BigInteger

/**
 * Returns the result of multiplying this number and [multiplicand], modulo the given [modulus].
 *
 * @throws IllegalArgumentException If [modulus] is negative or zero.
 */
fun Int.modMultiply(multiplicand: Int, modulus: Int): Int {
    require(modulus > 0) { "Modulus must be positive: $modulus" }
    return (mod(modulus) * multiplicand.mod(modulus)).mod(modulus)
}

/**
 * Returns the result of multiplying this number and [multiplicand], modulo the given [modulus].
 *
 * @throws IllegalArgumentException If [modulus] is negative or zero.
 */
fun Long.modMultiply(multiplicand: Long, modulus: Long): Long {
    require(modulus > 0L) { "Modulus must be positive: $modulus" }
    return (mod(modulus) * multiplicand.mod(modulus)).mod(modulus)
}

/**
 * Returns the result of multiplying this number and [multiplicand], modulo the given [modulus].
 *
 * @throws IllegalArgumentException If [modulus] is negative or zero.
 */
fun BigInteger.modMultiply(multiplicand: BigInteger, modulus: BigInteger): BigInteger {
    require(modulus > BigInteger.ZERO) { "Modulus must be positive: $modulus" }
    return (mod(modulus) * multiplicand.mod(modulus)).mod(modulus)
}
