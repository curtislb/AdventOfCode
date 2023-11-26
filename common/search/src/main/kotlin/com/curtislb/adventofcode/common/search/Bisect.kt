package com.curtislb.adventofcode.common.search

/**
 * Searches for and returns the least positive integer value from [knownFalse] to [knownTrue] for
 * which the given [predicate] function returns `true`.
 *
 * The [predicate] function must return `false` for all values in [knownFalse]..[knownTrue] less
 * than some integer `n` and return `true` for all values in the range greater than or equal to `n`.
 * Otherwise, the behavior of this function is undefined.
 *
 * If [knownTrue] is `null`, this function instead searches forward from [knownFalse], up to
 * [Long.MAX_VALUE], and returns -1 if no value is found for which [predicate]  returns `true`.
 *
 * @throws IllegalArgumentException If [knownFalse] is negative, or if [knownTrue] is less than or
 *  equal to [knownFalse].
 */
fun bisect(
    knownFalse: Long = 0L,
    knownTrue: Long? = null,
    predicate: (value: Long) -> Boolean
): Long {
    require(knownFalse >= 0L) { "Known false value must be non-negative: $knownFalse" }
    require(knownTrue == null || knownFalse < knownTrue) {
        "Known false value must be less than known true value: $knownFalse >= $knownTrue"
    }

    var falseValue = knownFalse
    var trueValue = if (knownTrue != null) {
        knownTrue
    } else {
        // Search for a known true value
        var stepSize = 1L
        var value = knownFalse + 1L
        while (!predicate(value)) {
            if (value == Long.MAX_VALUE) {
                // No true value found
                return -1L
            }

            // Found new largest known false value
            falseValue = value

            // Increase value (up to MAX_VALUE) by doubling step size
            stepSize = if (stepSize > 4611686018427387903L) Long.MAX_VALUE else stepSize * 2
            value = if (Long.MAX_VALUE - stepSize < value) Long.MAX_VALUE else value + stepSize
        }
        value
    }

    // Binary search over values to find the first true value
    while (falseValue < trueValue - 1L) {
        val midValue = falseValue + (trueValue - falseValue) / 2L
        if (predicate(midValue)) {
            trueValue = midValue
        } else {
            falseValue = midValue
        }
    }
    return trueValue
}
