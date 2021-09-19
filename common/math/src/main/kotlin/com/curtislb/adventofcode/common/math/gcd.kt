package com.curtislb.adventofcode.common.math

/**
 * Returns the largest positive integer that evenly divides both [m] and [n].
 *
 * @throws IllegalArgumentException If either [m] or [n] is negative or 0.
 */
fun greatestCommonDivisor(m: Long, n: Long): Long {
    require(m > 0L) { "First argument must be positive: $m" }
    require(n > 0L) { "Second argument must be positive: $n" }
    var a = m
    var b = n
    while (b != 0L) {
        a = b.also { b = a % b }
    }
    return a
}

/**
 * Returns the largest positive integer that evenly divides both [m] and [n].
 *
 * @throws IllegalArgumentException If either [m] or [n] is negative or 0.
 */
fun greatestCommonDivisor(m: Int, n: Int): Int = greatestCommonDivisor(m.toLong(), n.toLong()).toInt()
