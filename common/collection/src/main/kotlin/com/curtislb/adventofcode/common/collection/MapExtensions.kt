package com.curtislb.adventofcode.common.collection

/**
 * Returns a map with `(key, value)` entry pairs given by applying the [transform] function to each
 * element in this iterable.
 */
inline fun <E, K, V> Iterable<E>.mapToMap(transform: (item: E) -> Pair<K, V>): Map<K, V> {
    val map = mutableMapOf<K, V>()
    for (item in this) {
        map += transform(item)
    }
    return map
}

/**
 * Returns a map with `(key, value)` entry pairs given by applying the [transform] function to each
 * entry in the original map.
 */
inline fun <K, V, A, B> Map<K, V>.mapToMap(
    transform: (entry: Map.Entry<K, V>) -> Pair<A, B>
): Map<A, B> {
    return entries.mapToMap(transform)
}

/**
 * Returns a mutable set by applying the [transform] function to each element in this iterable.
 */
inline fun <T, R> Iterable<T>.mapToMutableSet(transform: (item: T) -> R): MutableSet<R> {
    val set = mutableSetOf<R>()
    for (item in this) {
        set.add(transform(item))
    }
    return set
}

/**
 * Returns a set by applying the [transform] function to each element in this iterable.
 */
inline fun <T, R> Iterable<T>.mapToSet(transform: (item: T) -> R): Set<R> =
    mapToMutableSet(transform)
