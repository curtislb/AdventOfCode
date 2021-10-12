package com.curtislb.adventofcode.common.search

/**
 * Returns a pair of values in this iterable that sum to [targetSum], or `null` if there are no such
 * values.
 */
fun Iterable<Int>.findPairSum(targetSum: Int): Pair<Int, Int>? {
    val pairSum = map { it.toLong() }.findPairSum(targetSum.toLong())
    return pairSum?.let { Pair(it.first.toInt(), it.second.toInt()) }
}

/**
 * Returns a pair of values in this iterable that sum to [targetSum], or `null` if there are no such
 * values.
 */
fun Iterable<Long>.findPairSum(targetSum: Long): Pair<Long, Long>? {
    fold(mutableSetOf<Long>()) { seenValues, value ->
        // Check previously seen values for the necessary remaining value.
        val remainder = targetSum - value
        if (remainder in seenValues) {
            return Pair(remainder, value)
        }

        // Keep track of all previously seen values.
        seenValues.add(value)
        seenValues
    }

    // No pair found.
    return null
}
