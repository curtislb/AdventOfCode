package com.curtislb.adventofcode.common.iteration

/**
 * Simulates [levelCount] nested `for` loops over the given list of [items].
 *
 * For each innermost loop iteration, the [process] function is applied to [levelCount] items from
 * the [items] list, corresponding to the current index for each loop level (outer to inner). This
 * continues until the outermost loop is exhausted or until [process] returns `true`, indicating
 * that the function should return early.
 *
 * If [overlapIndices] is `true`, each nested level will loop over the full range of valid indices:
 *
 * ```
 * for (i_0 in indices) {
 *     for (i_1 in indices) {
 *         ...
 *         for (i_n in indices) {
 *             if (process(listOf(items[i_0], items[i_1], ..., items[i_n]))) {
 *                 return
 *             }
 *         }
 *     }
 * }
 * ```
 *
 * If [overlapIndices] is `false`, the indices will be strictly increasing across nested levels:
 *
 * ```
 * for (i_0 in 0..(lastIndex - n)) {
 *     for (i_1 in (i_0 + 1)..(lastIndex - n + 1)) {
 *         ...
 *         for (i_n in (i_n_minus_1 + 1)..lastIndex) {
 *             if (process(listOf(items[i_0], items[i_1], ..., items[i_n]))) {
 *                 return
 *             }
 *         }
 *     }
 * }
 * ```
 *
 * @throws IllegalArgumentException If [levelCount] is negative, or if [overlapIndices] is `false`
 *  and [levelCount] is greater than the size of [items].
 */
inline fun <T> nestedLoop(
    items: List<T>,
    levelCount: Int,
    overlapIndices: Boolean = true,
    process: (items: List<T>) -> Boolean
) {
    if (levelCount == 0) {
        return
    }

    require(levelCount > 0) { "Level count must be non-negative: $levelCount" }
    require(overlapIndices || levelCount <= items.size) {
        "Level count can't exceed size of items list: $levelCount > ${items.size}"
    }

    val indexArray = IntArray(levelCount) { if (overlapIndices) 0 else it }
    val maxOutermostIndex = if (overlapIndices) items.size - 1 else items.size - levelCount
    while (indexArray[0] <= maxOutermostIndex) {
        // Process the current iteration and terminate if needed
        if (process(indexArray.map { items[it] })) {
            return
        }

        // Increment the rightmost index possible without overflowing
        var carryIndex = 0
        for (i in indexArray.lastIndex downTo 0) {
            if (i == 0) {
                indexArray[i]++
            } else {
                val maxIndex = if (overlapIndices) items.size - 1 else items.size - levelCount + i
                if (indexArray[i] < maxIndex) {
                    indexArray[i]++
                    carryIndex = i
                    break
                }
            }
        }

        // Roll over each index following the one that was incremented
        for (i in (carryIndex + 1)..indexArray.lastIndex) {
            indexArray[i] = if (overlapIndices) 0 else indexArray[i - 1] + 1
        }
    }
}
