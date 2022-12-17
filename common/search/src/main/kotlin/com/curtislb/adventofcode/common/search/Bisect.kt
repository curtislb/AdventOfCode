package com.curtislb.adventofcode.common.search

/**
 * Returns the first integer from [knownFalse] to [knownTrue] (inclusive) for which the [isTrue]
 * function switches from returning `false` to returning `true`, or `null` if no such value exists.
 *
 * If [knownTrue] is `null`, this function will search from [knownFalse] to [Long.MAX_VALUE].
 */
fun bisect(
    knownFalse: Long = 0L,
    knownTrue: Long? = null,
    isTrue: (value: Long) -> Boolean
): Long? {
    var falseValue = knownFalse
    var trueValue = if (knownTrue != null) {
        knownTrue
    } else {
        // Search for a known true value
        var stepSize = 1L
        var value = knownFalse + 1L
        while (value > knownFalse && !isTrue(value)) {
            if (value == Long.MAX_VALUE) {
                // No true value found up to MAX_VALUE
                return null
            }
            // Increase value by doubling step size, without exceeding MAX_VALUE
            stepSize *= 2
            value = if (Long.MAX_VALUE - stepSize < value) Long.MAX_VALUE else value + stepSize
        }
        value
    }

    // Known true value should be greater than known false value
    if (trueValue <= falseValue) {
        return null
    }

    // Binary search over values to find the first true value
    while (falseValue < trueValue - 1L) {
        val midValue = falseValue + (trueValue - falseValue) / 2L
        if (isTrue(midValue)) {
            trueValue = midValue
        } else {
            falseValue = midValue
        }
    }
    return trueValue
}
