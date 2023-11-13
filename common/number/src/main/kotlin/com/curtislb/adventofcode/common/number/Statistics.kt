package com.curtislb.adventofcode.common.number

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
        val pivotValue = list[minIndex]

        // Partition elements relative to the pivot
        var left = minIndex
        var right = maxIndex + 1
        while (left < right) {
            // Search for the first element greater than the pivot
            do {
                left++
            } while (left < maxIndex && list[left] < pivotValue)

            // Search for the last element less than the pivot
            do {
                right--
            } while (right > minIndex && list[right] > pivotValue)

            // If misplaced elements are found, swap their positions
            if (left < right) {
                list[left] = list[right].also { list[right] = list[left] }
            }
        }

        // Check if we've found the median
        if (right == medianIndex) {
            return pivotValue
        }

        // Swap the pivot into its final sorted position
        list[pivotIndex] = list[right].also { list[right] = list[pivotIndex] }

        // Repeat the process for the appropriate sublist
        if (medianIndex < right) {
            maxIndex = right - 1
        } else {
            minIndex = right + 1
        }
    }
}
