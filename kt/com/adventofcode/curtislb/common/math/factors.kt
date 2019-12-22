package com.adventofcode.curtislb.common.math

/**
 * TODO
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
 * TODO
 */
fun leastCommonMultiple(m: Int, n: Int): Int {
    assert(m >= 0)
    assert(n >= 0)
    return m / greatestCommonDivisor(m, n) * n
}
