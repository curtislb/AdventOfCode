package com.curtislb.adventofcode.common.math

import kotlin.math.floor
import kotlin.math.log
import kotlin.math.log10
import kotlin.math.log2

/**
 * Returns the number of digits in this non-negative integer when written in the given [base].
 *
 * @throws IllegalArgumentException If this number is negative or [base] is less than 2.
 */
fun Int.countDigits(base: Int = 10): Int {
    require(this >= 0) { "Number must be non-negative: $this" }
    require(base >= 2) { "Base must be at least 2: $base" }

    // The number zero is written as "0" in all bases.
    if (this == 0) {
        return 1
    }

    // Use specialized log functions for common bases.
    val baseLog = when (base) {
        2 -> log2(toDouble())
        10 -> log10(toDouble())
        else -> log(toDouble(), base.toDouble())
    }
    return floor(baseLog).toInt() + 1
}

/**
 * Returns the number of digits in this non-negative integer when written in the given [base].
 *
 * @throws IllegalArgumentException If this number is negative or [base] is less than 2.
 */
fun Long.countDigits(base: Int = 10): Int {
    require(this >= 0L) { "Number must be non-negative: $this" }
    require(base >= 2) { "Base must be at least 2: $base" }

    // The number zero is written as "0" in all bases.
    if (this == 0L) {
        return 1
    }

    // Use specialized log functions for common bases.
    val baseLog = when (base) {
        2 -> log2(toDouble())
        10 -> log10(toDouble())
        else -> log(toDouble(), base.toDouble())
    }
    return floor(baseLog).toInt() + 1
}
