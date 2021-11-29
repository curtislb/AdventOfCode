package com.curtislb.adventofcode.common.search

/**
 * Returns the first index in the range from [knownFalse] to [knownTrue] (inclusive) for which the
 * function [predicate] switches from returning `false` to returning `true`, or `null` if no such
 * index exists.
 *
 * If [knownTrue] is set to `null`, the search will instead be conducted forward from [knownFalse]
 * without bound.
 */
fun bisectIndex(
    knownFalse: Long = 0L,
    knownTrue: Long? = null,
    predicate: ((index: Long) -> Boolean)
): Long? {
    // If only a false index is known, search for a true index.
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

    // Known true index should be greater than known false index.
    if (trueIndex <= falseIndex) {
        return null
    }

    // Binary search over indices to find the first true index.
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
