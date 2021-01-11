package com.curtislb.adventofcode.common.collection

/**
 * Simulates a [levelCount] nested loop over the items in this list.
 *
 * For each innermost loop iteration, the [process] function is applied to a list of pairs representing the current
 * index and corresponding item for each loop level (from outermost to innermost). This continues until the outermost
 * loop is exhausted or until [process] returns `true`, indicating that the function should terminate early.
 *
 * If [overlapIndices] is `true`, each level will loop over the full range of valid indices:
 * ```
 * for (i[0] in indices) {
 *     for (i[1] in indices) {
 *         ...
 *         for (i[n] in indices) {
 *             if (process(listOf(Pair(i[0], this[i[0]]), Pair(i[1], this[i[1]]), ..., Pair(i[n], this[i[n]])))) {
 *                 return
 *             }
 *         }
 *     }
 * }
 * ```
 *
 * Otherwise, if [overlapIndices] is `false`, the indices at each loop level will be strictly increasing:
 * ```
 * for (i[0] in 0..(lastIndex - n)) {
 *     for (i[1] in (i[0] + 1)..(lastIndex - n + 1)) {
 *         ...
 *         for (i[n] in (i[n-1] + 1)..lastIndex) {
 *             if (process(listOf(Pair(i[0], this[i[0]]), Pair(i[1], this[i[1]]), ..., Pair(i[n], this[i[n]])))) {
 *                 return
 *             }
 *         }
 *     }
 * }
 * ```
 */
fun <T> List<T>.forEachNested(
    levelCount: Int,
    overlapIndices: Boolean = true,
    process: (indexedItems: List<Pair<Int, T>>) -> Boolean
) {
    require(overlapIndices || levelCount <= size) {
        "Can't nest $levelCount levels with non-overlapping indices for a list of size $size."
    }

    val indices = IntArray(levelCount) { if (overlapIndices) 0 else it }
    while (indices.isValid(size, levelCount, overlapIndices)) {
        // Process the current iteration and terminate if necessary.
        if (process(indices.map { Pair(it, this[it]) })) {
            return
        }

        // Advance to the next innermost loop iteration.
        indices.increment(size, levelCount, overlapIndices)
    }
}

/**
 * Checks if this index array is valid for the given [listSize], according to the parameters of the [forEachNested]
 * function.
 */
private fun IntArray.isValid(listSize: Int, levelCount: Int, overlapIndices: Boolean): Boolean {
    for (i in 0..lastIndex) {
        // Each index must be in a valid range.
        if (this[i] !in 0..maxIndexAt(i, listSize, levelCount, overlapIndices)) {
            return false
        }

        // Indices must be non-overlapping if specified.
        if (i > 0 && !overlapIndices && this[i - 1] >= this[i]){
            return false
        }
    }

    // All indices are valid.
    return true
}

/**
 * Increments this index array, according to the parameters of the [forEachNested] function.
 */
private fun IntArray.increment(listSize: Int, levelCount: Int, overlapIndices: Boolean) {
    // Increment the rightmost index possible without overflowing.
    var carryIndex = 0
    for (i in lastIndex downTo 0) {
        if (i == 0) {
            this[i]++
        } else if (this[i] < maxIndexAt(i, listSize, levelCount, overlapIndices)) {
            this[i]++
            carryIndex = i
            break
        }
    }

    // Roll over each index following the one that was incremented.
    for (i in carryIndex + 1..lastIndex) {
        this[i] = if (overlapIndices) 0 else this[i - 1] + 1
    }
}

/**
 * Returns the maximum index for a given loop [level], according to the parameters of the [forEachNested] function.
 */
private fun maxIndexAt(level: Int, listSize: Int, levelCount: Int, overlapIndices: Boolean): Int {
    return if (overlapIndices) listSize - 1 else listSize - levelCount + level
}
