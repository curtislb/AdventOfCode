package com.adventofcode.curtislb.common.search

/**
 * Finds the first index for which a predicate switches from `false` to `true`.
 * @param knownFalse An index for which [predicate] is known to return `false`. Sets the lower bound of the search.
 * @param knownTrue An index for which [predicate] is known to return `true`. If [knownTrue] is `null`, the search will
 *  be conducted forward from [knownFalse] without bound.
 * @param predicate A boolean function over [Long] values in the range `knownFalse..knownTrue`, or all [Long] values
 *  `>= knownFalse` if `knownTrue == null`. In this range, there must be some [Long] index `i` such that
 *  `predicate(j) == (j >= i)` for all `j` in the range.
 * @return The least index in the range `knownFalse..knownTrue` such that `predicate(index) == true`, or `null` if no
 *  such index exists.
 * @throws IllegalArgumentException If no [predicate] function is provided.
 */
fun bisectIndex(knownFalse: Long = 0L, knownTrue: Long? = null, predicate: ((Long) -> Boolean)? = null): Long? {
    if (predicate == null) {
        throw IllegalArgumentException("Must specify a predicate.")
    }

    // If only a false index is known, search for a true index
    var falseIndex = knownFalse
    var trueIndex = if (knownTrue != null) {
        knownTrue
    } else {
        var stepSize = 1L
        var index = knownFalse + 1L
        while (index > knownFalse && !predicate(index)) {
            stepSize *= 2L
            index += stepSize
        }
        index
    }

    // Known true index should be greater than known false index
    if (trueIndex <= falseIndex) {
        return null
    }

    // Binary search over indices to find the first true index
    while (falseIndex < trueIndex - 1L) {
        val midIndex = falseIndex + (trueIndex - falseIndex) / 2L
        if (predicate(midIndex)) {
            trueIndex = midIndex
        } else {
            falseIndex = midIndex
        }
    }
    return trueIndex
}
