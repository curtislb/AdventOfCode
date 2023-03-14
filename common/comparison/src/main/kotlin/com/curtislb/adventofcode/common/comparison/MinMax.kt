package com.curtislb.adventofcode.common.comparison

/**
 * The minimum and maximum of a group of comparable values.
 *
 * @param min The minimum value from the original group.
 * @param max The maximum value from the original group.
 */
data class MinMax<T>(val min: T, val max: T)

/**
 * Returns a [MinMax] with the given [min] and [max] values, or `null` if either argument is `null`.
 */
internal fun <T> createMinMaxOrNull(min: T?, max: T?): MinMax<T>? =
    if (min == null || max == null) null else MinMax(min, max)

/**
 * Returns the minimum and maximum elements from this iterable, or `null` there are no elements.
 */
fun <T : Comparable<T>> Iterable<T>.minMaxOrNull(): MinMax<T>? {
    var minElement: T? = null
    var maxElement: T? = null
    for (element in this) {
        minElement = minElement?.coerceAtMost(element) ?: element
        maxElement = maxElement?.coerceAtLeast(element) ?: element
    }
    return createMinMaxOrNull(minElement, maxElement)
}

/**
 * Returns the elements that produce the minimum and maximum value, respectively, after applying the
 * [transform] function to each element in this iterable, or `null` if there are no elements.
 */
fun <T, R : Comparable<R>> Iterable<T>.minMaxByOrNull(transform: (element: T) -> R): MinMax<T>? {
    var minElement: T? = null
    var maxElement: T? = null
    var minValue: R? = null
    var maxValue: R? = null

    for (element in this) {
        val value = transform(element)

        // Update minimum element and value if necessary
        minValue?.let { currentMinValue ->
            if (value < currentMinValue) {
                minElement = element
                minValue = value
            }
        } ?: run {
            minElement = element
            minValue = value
        }

        // Update maximum element and value if necessary
        maxValue?.let { currentMaxValue ->
            if (value > currentMaxValue) {
                maxElement = element
                maxValue = value
            }
        } ?: run {
            maxElement = element
            maxValue = value
        }
    }

    return createMinMaxOrNull(minElement, maxElement)
}
