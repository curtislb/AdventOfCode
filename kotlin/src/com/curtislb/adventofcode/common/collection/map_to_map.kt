package com.curtislb.adventofcode.common.collection

/**
 * Returns a map with an entry for each [Pair] given by applying the [transform] function to an element in the original
 * collection.
 */
fun <E, K, V> Collection<E>.mapToMap(transform: (item: E) -> Pair<K, V>): Map<K, V> {
    val mapBuilder = mutableMapOf<K, V>()
    forEach { item ->
        val (key, value) = transform(item)
        mapBuilder[key] = value
    }
    return mapBuilder
}

/**
 * Returns a map with an entry for each [Pair] given by applying the [transform] function to an entry in the original
 * map.
 */
fun <K, V, A, B> Map<K, V>.mapToMap(transform: (entry: Map.Entry<K, V>) -> Pair<A, B>): Map<A, B> {
    return entries.mapToMap(transform)
}
