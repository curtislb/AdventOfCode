package com.curtislb.adventofcode.common.math

import java.math.BigInteger

/**
 * Returns the result of multiplying this number and [multiplicand], modulo the given [modulus].
 *
 * @throws IllegalArgumentException If [modulus] is zero.
 */
fun Long.modMultiply(multiplicand: Long, modulus: Long): Long {
    require(modulus != 0L) { "Modulus must be nonzero." }
    return ((this.mod(modulus)) * (multiplicand.mod(modulus))).mod(modulus)
}

/**
 * Returns the result of multiplying this number and [multiplicand], modulo the given [modulus].
 *
 * @throws IllegalArgumentException If [modulus] is negative or zero.
 */
fun BigInteger.modMultiply(multiplicand: BigInteger, modulus: BigInteger): BigInteger {
    require(modulus > BigInteger.ZERO) { "Modulus must be positive: $modulus" }
    return ((this.mod(modulus)) * (multiplicand.mod(modulus))).mod(modulus)
}

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

/**
 * Returns the product of all values in this iterable, or 1 for an empty iterable.
 */
fun Iterable<Int>.product(): Int = fold(1) { product, value -> product * value }

/**
 * Returns the product of all values in this iterable, or 1 for an empty iterable.
 */
fun Iterable<Long>.product(): Long = fold(1L) { product, value -> product * value }

/**
 * Returns the product of all values in this iterable, or [BigInteger.ONE] for an empty iterable.
 */
fun Iterable<BigInteger>.product(): BigInteger =
    fold(BigInteger.ONE) { product, value -> product * value }
