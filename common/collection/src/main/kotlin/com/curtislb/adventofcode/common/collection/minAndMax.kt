package com.curtislb.adventofcode.common.collection

import lombok.Generated

/**
 * Returns a pair of the elements that produce the minimum and maximum value, respectively, after applying the
 * [transform] function to each element in this iterable, or the pair `(null, null)` if there are no elements.
 */
@Generated
inline fun <T, R : Comparable<R>> Iterable<T>.minAndMaxByOrNull(transform: (element: T) -> R): Pair<T?, T?> {
    var minElement: T? = null
    var maxElement: T? = null
    var minValue: R? = null
    var maxValue: R? = null

    forEach { element ->
        val value = transform(element)

        // Update minimum element and value if necessary.
        minValue?.let {
            if (value < it) {
                minElement = element
                minValue = value
            }
        }

        // Update maximum element and value if necessary.
        maxValue?.let {
            if (value > it) {
                maxElement = element
                maxValue = value
            }
        }
    }

    return Pair(minElement, maxElement)
}

/**
 * Returns a pair of the minimum and maximum value, respectively, after applying the [transform] function to each
 * element in this iterable, or the pair `(null, null)` if there are no elements.
 */
@Generated
inline fun <T, R : Comparable<R>> Iterable<T>.minAndMaxOfOrNull(transform: (element: T) -> R): Pair<R?, R?> {
    var minValue: R? = null
    var maxValue: R? = null
    forEach { element ->
        val value = transform(element)
        minValue = minValue?.coerceAtMost(value) ?: value
        maxValue = maxValue?.coerceAtLeast(value) ?: value
    }
    return Pair(minValue, maxValue)
}
