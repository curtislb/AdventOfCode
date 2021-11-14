package com.curtislb.adventofcode.year2020.day10.joltage

import com.curtislb.adventofcode.common.collection.Counter
import com.curtislb.adventofcode.common.collection.FifoCache

/**
 * A collection of distinct joltage adapters that may be chained to connect a compatible device to
 * an outlet.
 *
 * @param ratings A set of values representing the joltage rating for each adapter.
 *
 * @throws IllegalArgumentException If the joltage rating of any adapter is negative or zero.
 */
class JoltageAdapters(ratings: Set<Int>) {
    /**
     * A sorted list of joltage ratings, including a rating of 0 jolts for the outlet.
     */
    private val sortedRatings: List<Int> = mutableListOf(0).apply {
        ratings.forEach { rating ->
            require(rating > 0) { "Adapter rating must be positive: $rating" }
            add(rating)
        }
        sort()
    }

    /**
     * Returns the count of each joltage rating difference between subsequent adapters in a sorted
     * adapter chain.
     *
     * Adapters are sorted in increasing order by joltage rating and connected to an outlet with an
     * effective rating of 0 jolts. The difference between the minimum adapter rating and the
     * outlet's rating is included in the result.
     */
    fun countRatingDifferences(): Counter<Int> {
        val counter = Counter<Int>()
        for (index in 1..sortedRatings.lastIndex) {
            val difference = sortedRatings[index] - sortedRatings[index - 1]
            counter[difference]++
        }
        return counter
    }

    /**
     * Returns the number of distinct ways that these adapters can be arranged to connect a device
     * to an outlet.
     *
     * A valid arrangement consists of a subset of adapters connected to an outlet in increasing
     * order by joltage rating, such that the difference in ratings between any two connected
     * adapters is at most 3 jolts. For this purpose, the outlet is treated as an adapter with an
     * effective rating of 0 jolts.
     */
    fun countArrangements(): Long {
        // Dynamically count the number of arrangements up to (and including) each adapter.
        val prevCounts = FifoCache<Long>(3).apply { add(1L) }
        for (i in 1..sortedRatings.lastIndex) {
            // Combine counts from previous adapters that can be connected to the current one.
            var count = 0L
            prevCounts.forEachIndexed { j, prevCount ->
                if (sortedRatings[i] - sortedRatings[i - prevCounts.size + j] <= 3) {
                    count += prevCount
                }
            }
            prevCounts.add(count)
        }

        // The count for the last adapter is the same as the overall count.
        return prevCounts.last()
    }
}
