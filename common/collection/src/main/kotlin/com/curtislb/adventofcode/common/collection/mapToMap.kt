package com.curtislb.adventofcode.common.collection

import lombok.Generated

/**
 * Returns a map from the pairs given by applying the [transform] function to each element in the
 * original collection.
 */
@Generated
inline fun <E, K, V> Collection<E>.mapToMap(transform: (item: E) -> Pair<K, V>): Map<K, V> {
    return mutableMapOf<K, V>().apply { this@mapToMap.forEach { this += transform(it) } }
}

/**
 * Returns a map from the pairs given by applying the [transform] function to each entry in the
 * original map.
 */
@Generated
inline fun <K, V, A, B> Map<K, V>.mapToMap(
    transform: (entry: Map.Entry<K, V>) -> Pair<A, B>
): Map<A, B> {
    return entries.mapToMap(transform)
}
