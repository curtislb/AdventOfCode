package com.curtislb.adventofcode.common.comparison

/**
 * The minimum and maximum of a group of comparable values.
 *
 * @param min The minimum value from the original group.
 * @param max The maximum value from the original group.
 */
data class MinMax<T>(val min: T, val max: T)

/**
 * Returns the minimum and maximum elements from this iterable, or `null` there are no elements.
 */
fun <T : Comparable<T>> Iterable<T>.minMaxOrNull(): MinMax<T>? {
    var minElement: T? = null
    var maxElement: T? = null
    for (element in this) {
        minElement = if (minElement == null) element else element.coerceAtMost(minElement)
        maxElement = if (maxElement == null) element else element.coerceAtLeast(maxElement)
    }
    return if (minElement == null) null else MinMax(minElement, maxElement!!)
}

/**
 * Returns the elements that produce the minimum and maximum value, respectively, after applying the
 * [transform] function to each element in this iterable, or `null` if there are no elements.
 */
inline fun <T, R : Comparable<R>> Iterable<T>.minMaxByOrNull(
    transform: (element: T) -> R
): MinMax<T>? {
    var minElement: T? = null
    var maxElement: T? = null
    var minValue: R? = null
    var maxValue: R? = null

    for (element in this) {
        val value = transform(element)

        // Update minimum element and value if necessary
        if (minValue == null || value < minValue) {
            minElement = element
            minValue = value
        }

        // Update maximum element and value if necessary
        if (maxValue == null || value > maxValue) {
            maxElement = element
            maxValue = value
        }
    }

    return if (minElement == null) null else MinMax(minElement, maxElement!!)
}
