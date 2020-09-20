package com.curtislb.adventofcode.common.collection

/**
 * Returns a map with an entry for each pair given by applying the [transform] function to an element in the original
 * collection.
 */
inline fun <E, K, V> Collection<E>.mapToMap(transform: (item: E) -> Pair<K, V>): Map<K, V> {
    return mutableMapOf<K, V>().apply { this@mapToMap.forEach { this += transform(it) } }
}

/**
 * Returns a map with an entry for each pair given by applying the [transform] function to an entry in the original map.
 */
inline fun <K, V, A, B> Map<K, V>.mapToMap(transform: (entry: Map.Entry<K, V>) -> Pair<A, B>): Map<A, B> {
    return entries.mapToMap(transform)
}
