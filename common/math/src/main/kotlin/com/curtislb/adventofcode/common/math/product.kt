package com.curtislb.adventofcode.common.math

import java.math.BigInteger

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
