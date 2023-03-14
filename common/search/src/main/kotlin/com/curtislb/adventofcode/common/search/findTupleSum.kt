package com.curtislb.adventofcode.common.search

import com.curtislb.adventofcode.common.collection.Counter
import com.curtislb.adventofcode.common.iteration.nestedLoop

/**
 * Returns a list of [count] values in this iterable that sum to [targetSum], or `null` if there are
 * no such values.
 */
fun Iterable<Int>.findTupleSum(count: Int, targetSum: Int): List<Int>? {
    val tupleSum = map { it.toLong() }.findTupleSum(count, targetSum.toLong())
    return tupleSum?.map { it.toInt() }
}

/**
 * Returns a list of [count] values in this iterable that sum to [targetSum], or `null` if there are
 * no such values.
 */
fun Iterable<Long>.findTupleSum(count: Int, targetSum: Long): List<Long>? {
    require(count >= 0) { "Count must be non-negative: $count" }
    return when (count) {
        0 -> if (targetSum == 0L) emptyList() else null
        1 -> find { it == targetSum }?.let { listOf(it) }
        2 -> findPairSum(targetSum)?.toList()
        else -> {
            // Count and list all unique values
            val counter = Counter<Long>().apply { addAll(this@findTupleSum) }
            val uniqueValues = counter.keys.toList()

            var result: List<Long>? = null
            nestedLoop(
                items = uniqueValues,
                levelCount = count - 1,
                overlapIndices = false
            ) { values ->
                // Pick `count - 1` distinct values, then check for the remaining value
                val remainder = targetSum - values.sum()
                if (remainder !in counter) {
                    false // Keep iterating
                } else {
                    // Ensure the same values aren't used multiple times
                    val candidateValues = values.toMutableList().apply { add(remainder) }
                    val candidateCounts = Counter<Long>().apply { addAll(candidateValues) }
                    if (candidateCounts in counter) {
                        result = candidateValues
                        true // Stop iterating
                    } else {
                        false // Keep iterating
                    }
                }
            }
            result
        }
    }
}
