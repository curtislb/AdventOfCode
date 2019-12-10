package com.adventofcode.curtislb.common.math

/**
 * TODO
 */
fun Int.pow(exponent: Int): Int = (1..exponent).fold(1) { product, _ -> product * this }
