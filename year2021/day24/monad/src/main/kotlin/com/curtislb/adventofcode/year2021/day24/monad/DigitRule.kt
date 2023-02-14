package com.curtislb.adventofcode.year2021.day24.monad

/**
 * A rule describing the relationship between two digits in a number.
 *
 * @param fromIndex The big-endian index of the base digit.
 * @param toIndex The big-endian index of the dependent digit.
 * @param increase The arithmetic difference given by subtracting the value of the digit at
 *  [fromIndex] from that of the digit at [toIndex].
 */
data class DigitRule(val fromIndex: Int, val toIndex: Int, val increase: Int)
