package com.adventofcode.curtislb.common.math

/**
 * Finds the greatest common divisor (GCD) of two whole numbers.
 * @param m A positive [Int] value.
 * @param n A second positive [Int] value.
 * @return The largest whole number that evenly divides both [m] and [n].
 */
fun greatestCommonDivisor(m: Int, n: Int): Int {
    assert(m >= 0)
    assert(n >= 0)
    var a = m
    var b = n
    while (b != 0) {
        val temp = b
        b = a % b
        a = temp
    }
    return a
}

/**
 * Finds the least common multiple (LCM) of two whole numbers.
 * @param m A positive [Int] value.
 * @param n A second positive [Int] value.
 * @return The smallest whole number that can be evenly divided by both [m] and [n].
 */
fun leastCommonMultiple(m: Int, n: Int): Int {
    assert(m >= 0)
    assert(n >= 0)
    return m / greatestCommonDivisor(m, n) * n
}
