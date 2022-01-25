package com.curtislb.adventofcode.common.search

/**
 * Returns a contiguous list of [minSize] or more numbers in this iterable that sum to [targetSum].
 *
 * Both [targetSum] and all values in this iterable must be non-negative.
 *
 * @throws IllegalArgumentException If [targetSum] or any value in this iterable is negative.
 */
fun Iterable<Long>.findContiguousSubsetSum(targetSum: Long, minSize: Int = 0): List<Long>? {
    require(targetSum >= 0L) { "Target sum must be non-negative: $targetSum" }

    // Use a resizing window to scan for a subset that matches the target.
    val deque = ArrayDeque<Long>()
    var total = 0L
    for (value in this) {
        require(value >= 0L) { "Each value must be non-negative: $value" }

        // Shrink the window from the left while it exceeds the target.
        while (total > targetSum) {
            total -= deque.removeFirst()
        }
        if (total == targetSum && deque.size >= minSize) {
            return deque.toList()
        }

        // Grow the window to the right while it is below the target.
        total += value
        deque.addLast(value)
        if (total == targetSum && deque.size >= minSize) {
            return deque.toList()
        }
    }

    // Failed to find a valid subset.
    return null
}
