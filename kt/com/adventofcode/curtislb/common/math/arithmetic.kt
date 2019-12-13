package com.adventofcode.curtislb.common.math

/**
 * Calculates the integer power of this number to the given exponent.
 * @receiver The integer base that will be raised to the given power.
 * @param exponent The integer exponent to which the base will be raised.
 * @return The integer value of [this]^[exponent].
 */
fun Int.pow(exponent: Int): Int = (1..exponent).fold(1) { product, _ -> product * this }
