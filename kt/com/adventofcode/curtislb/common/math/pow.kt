package com.adventofcode.curtislb.common.math

/**
 * Calculates the [Long] power of this number to the given exponent.
 * @receiver The [Long] base value to be raised to [exponent].
 * @param exponent An [Int] value representing the power to which this number will be raised.
 * @return The [Long] value of [this]^[exponent].
 */
fun Long.pow(exponent: Int): Long = (1..exponent).fold(1L) { product, _ -> product * this }

/**
 * Calculates the [Int] power of this number to the given exponent.
 * @receiver The [Int] base value to be raised to [exponent].
 * @param exponent An [Int] value representing the power to which this number will be raised.
 * @return The [Int] value of [this]^[exponent].
 */
fun Int.pow(exponent: Int): Int = toLong().pow(exponent).toInt()
