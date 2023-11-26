package com.curtislb.adventofcode.common.search

/**
 * Returns a list of [minSize] or more contiguous values in the iterable that sum to [targetSum],
 * or `null` if are no such values.
 *
 * @throws IllegalArgumentException If [targetSum] or [minSize] is negative, or a negative value is
 *  encountered during iteration.
 */
fun Iterable<Long>.findNonNegativeSubarraySum(targetSum: Long, minSize: Int = 0): List<Long>? {
    require(targetSum >= 0L) { "Target sum must be non-negative: $targetSum" }
    require(minSize >= 0L) { "Minimum size must be non-negative: $minSize" }

    // Empty subarray has a sum of 0
    if (targetSum == 0L && minSize == 0) {
        return emptyList()
    }

    // Use a resizing window to scan for a subarray that sums to the target
    val deque = ArrayDeque<Long>()
    var total = 0L
    for (value in this) {
        require(value >= 0L) { "Each value must be non-negative: $value" }

        // Add value to the end of the window
        total += value
        deque.addLast(value)

        // Remove values from the start of the window (as needed)
        while (total > targetSum) {
            total -= deque.removeFirst()
        }

        // Check if the target sum is reached
        if (total == targetSum && deque.size >= minSize) {
            return deque.toList()
        }
    }

    // No subarray found
    return null
}

/**
 * Returns any two values in the iterable that sum to [targetSum], or `null` if there are no such
 * values.
 */
fun Iterable<Int>.findPairSum(targetSum: Int): Pair<Int, Int>? {
    // Iterate while keeping track of previously seen values
    val prevValues = mutableSetOf<Int>()
    for (value in this) {
        // Check if this and any previous value sum to the target
        val remainder = targetSum - value
        if (remainder in prevValues) {
            return Pair(remainder, value)
        }

        prevValues.add(value)
    }

    // No pair found
    return null
}

/**
 * Returns any two values in the iterable that sum to [targetSum], or `null` if there are no such
 * values.
 */
fun Iterable<Long>.findPairSum(targetSum: Long): Pair<Long, Long>? {
    // Iterate while keeping track of previously seen values
    val prevValues = mutableSetOf<Long>()
    for (value in this) {
        // Check if this and any previous value sum to the target
        val remainder = targetSum - value
        if (remainder in prevValues) {
            return Pair(remainder, value)
        }

        prevValues.add(value)
    }

    // No pair found
    return null
}

/**
 * Returns any three values in the iterable that sum to [targetSum], or `null` if there are no such
 * values.
 */
fun Iterable<Int>.findTripleSum(targetSum: Int): Triple<Int, Int, Int>? {
    // Sort the values into a list
    val sortedValues = sorted()

    // Fix each first value and search for more values to the right of it
    for (i in 0..(sortedValues.lastIndex - 2)) {
        // Find target sum for the second and third values
        val value1 = sortedValues[i]
        val remainder = targetSum - value1

        // Search for second and third values that sum to remainder
        var j = i + 1
        var k = sortedValues.lastIndex
        while (j < k) {
            val value2 = sortedValues[j]
            val value3 = sortedValues[k]
            val twoThreeSum = value2 + value3
            when {
                twoThreeSum < remainder -> j++
                twoThreeSum > remainder -> k--
                else -> return Triple(value1, value2, value3)
            }
        }
    }

    // No triple found
    return null
}

/**
 * Returns any three values in the iterable that sum to [targetSum], or `null` if there are no such
 * values.
 */
fun Iterable<Long>.findTripleSum(targetSum: Long): Triple<Long, Long, Long>? {
    // Sort the values into a list
    val sortedValues = sorted()

    // Fix first value and search for more values to the right
    for (i in 0..(sortedValues.lastIndex - 2)) {
        // Find target sum for the second and third values
        val value1 = sortedValues[i]
        val remainder = targetSum - value1

        // Search for second and third values that sum to remainder
        var j = i + 1
        var k = sortedValues.lastIndex
        while (j < k) {
            val value2 = sortedValues[j]
            val value3 = sortedValues[k]
            val twoThreeSum = value2 + value3
            when {
                twoThreeSum < remainder -> j++
                twoThreeSum > remainder -> k--
                else -> return Triple(value1, value2, value3)
            }
        }
    }

    // No triple found
    return null
}
