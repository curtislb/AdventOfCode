package com.curtislb.adventofcode.common.collection

/**
 * Returns the sums of all contiguous subsets of this list with the given [windowSize].
 *
 * @throws IllegalArgumentException If [windowSize] is negative or zero.
 */
fun List<Int>.windowSums(windowSize: Int): List<Int> {
    require(windowSize > 0) { "Window size must be positive: $windowSize" }

    val sums = mutableListOf<Int>()

    // Calculate the sum for each window
    val window = FifoCache<Int>(windowSize)
    var windowSum = 0
    forEach { value ->
        windowSum += if (window.isFull()) value - window.first() else value
        window.add(value)
        if (window.isFull()) {
            sums.add(windowSum)
        }
    }

    return sums
}
