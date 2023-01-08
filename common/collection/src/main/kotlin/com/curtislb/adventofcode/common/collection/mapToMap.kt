package com.curtislb.adventofcode.common.collection

/**
 * Returns a map from the pairs given by applying the [transform] function to each element in the
 * original iterable.
 */
inline fun <E, K, V> Iterable<E>.mapToMap(transform: (item: E) -> Pair<K, V>): Map<K, V> =
    mutableMapOf<K, V>().apply {
        for (item in this@mapToMap) {
            this += transform(item)
        }
    }

/**
 * Returns a map from the pairs given by applying the [transform] function to each entry in the
 * original map.
 */
inline fun <K, V, A, B> Map<K, V>.mapToMap(
    transform: (entry: Map.Entry<K, V>) -> Pair<A, B>
): Map<A, B> = entries.mapToMap(transform)
