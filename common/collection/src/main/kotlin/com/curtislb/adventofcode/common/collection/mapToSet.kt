package com.curtislb.adventofcode.common.collection

import lombok.Generated

/**
 * Returns a mutable set by applying the [transform] function to each element in this iterable.
 */
@Generated
inline fun <T, R> Iterable<T>.mapToMutableSet(transform: (item: T) -> R): MutableSet<R> =
    mutableSetOf<R>().apply {
        for (item in this@mapToMutableSet) {
            add(transform(item))
        }
    }

/**
 * Returns a set by applying the [transform] function to each element in this iterable.
 */
@Generated
inline fun <T, R> Iterable<T>.mapToSet(transform: (item: T) -> R): Set<R> =
    mapToMutableSet(transform)
