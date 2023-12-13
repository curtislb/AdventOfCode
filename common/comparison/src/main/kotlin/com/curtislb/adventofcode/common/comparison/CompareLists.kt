package com.curtislb.adventofcode.common.comparison

/**
 * Compares [list1] to [list2] in lexicographic order and returns one of the following:
 *
 * - A negative number if [list1] < [list2]
 * - A positive number if [list1] > [list2]
 * - Zero if [list1] == [list2]
 */
fun <T : Comparable<T>> compareLists(list1: List<T>, list2: List<T>): Int {
    // Compare by the first unequal element, from left to right
    for (index in 0..minOf(list1.lastIndex, list2.lastIndex)) {
        val result = list1[index].compareTo(list2[index])
        if (result != 0) {
            return result
        }
    }

    // Compare by size if elements are equal until the end of either list
    return list1.size.compareTo(list2.size)
}
