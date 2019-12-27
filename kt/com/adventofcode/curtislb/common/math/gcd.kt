package com.adventofcode.curtislb.common.math

/**
 * Finds the greatest common divisor (GCD) of two whole numbers.
 * @param m A positive [Long] value.
 * @param n A second positive [Long] value.
 * @return The largest whole number that evenly divides both [m] and [n].
 */
fun greatestCommonDivisor(m: Long, n: Long): Long {
    assert(m > 0L)
    assert(n > 0L)
    var a = m
    var b = n
    while (b != 0L) {
        val temp = b
        b = a % b
        a = temp
    }
    return a
}

/**
 * Finds the greatest common divisor (GCD) of two whole numbers.
 * @param m A positive [Int] value.
 * @param n A second positive [Int] value.
 * @return The largest whole number that evenly divides both [m] and [n].
 */
fun greatestCommonDivisor(m: Int, n: Int): Int = greatestCommonDivisor(m.toLong(), n.toLong()).toInt()
