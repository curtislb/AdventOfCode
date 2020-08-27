package com.curtislb.adventofcode.common.math

import kotlin.math.floor
import kotlin.math.log
import kotlin.math.log10
import kotlin.math.log2

/**
 * Returns the number of digits in this non-negative integer when written in the given [base].
 */
fun Long.countDigits(base: Int = 10): Int {
    assert(this >= 0L)
    assert(base >= 2)
    return when {
        this == 0L -> 1
        base == 2 -> floor(log2(toDouble())).toInt() + 1
        base == 10 -> floor(log10(toDouble())).toInt() + 1
        else -> floor(log(toDouble(), base.toDouble())).toInt() + 1
    }
}

/**
 * Returns the number of digits in this non-negative integer when written in the given [base].
 */
fun Int.countDigits(base: Int = 10): Int = toLong().countDigits(base)
