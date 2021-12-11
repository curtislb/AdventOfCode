package com.curtislb.adventofcode.common.math

/**
 * Returns the median element of this list, or `null` if the list is empty.
 *
 * If the list has an even number of elements, this function returns the median at index `size / 2`
 * in the sorted list.
 */
fun <T : Comparable<T>> List<T>.medianOrNull(): T? {
    // Check if there's a median to return
    if (size <= 1) {
        return firstOrNull()
    }

    // Shuffle the list to guarantee a random distribution of pivots
    val list = toMutableList().apply { shuffle() }

    // Pick a target index that works for even- and odd-length lists
    val medianIndex = size / 2

    var minIndex = 0
    var maxIndex = lastIndex
    while (true) {
        // Select the first element as the pivot (since the list is shuffled)
        val pivotIndex = minIndex
        val pivot = list[minIndex]

        // Partition elements relative to the pivot
        var leftIndex = minIndex
        var rightIndex = maxIndex + 1
        while (leftIndex < rightIndex) {
            // Search for the first element greater than the pivot
            do {
                leftIndex++
            } while (leftIndex < maxIndex && list[leftIndex] < pivot)

            // Search for the last element less than the pivot
            do {
                rightIndex--
            } while (rightIndex > minIndex && list[rightIndex] > pivot)

            // If misplaced elements are found, swap their positions
            if (leftIndex < rightIndex) {
                list[leftIndex] = list[rightIndex].also { list[rightIndex] = list[leftIndex] }
            }
        }

        // Check if we've found the median
        if (rightIndex == medianIndex) {
            return pivot
        }

        // Swap the pivot into its final sorted position
        list[pivotIndex] = list[rightIndex].also { list[rightIndex] = list[pivotIndex] }

        // Repeat the process for the appropriate sublist
        if (medianIndex < rightIndex) {
            maxIndex = rightIndex - 1
        } else {
            minIndex = rightIndex + 1
        }
    }
}
