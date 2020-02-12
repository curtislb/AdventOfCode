package com.curtislb.adventofcode.common.math

/**
 * Returns the largest whole number that evenly divides both [m] and [n].
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
 * Returns the largest whole number that evenly divides both [m] and [n].
 */
fun greatestCommonDivisor(m: Int, n: Int): Int = greatestCommonDivisor(m.toLong(), n.toLong()).toInt()