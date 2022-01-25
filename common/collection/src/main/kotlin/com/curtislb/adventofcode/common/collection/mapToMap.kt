package com.curtislb.adventofcode.common.collection

import lombok.Generated

/**
 * Returns a map from the pairs given by applying the [transform] function to each element in the
 * original collection.
 */
@Generated
inline fun <E, K, V> Collection<E>.mapToMap(transform: (item: E) -> Pair<K, V>): Map<K, V> =
    mutableMapOf<K, V>().apply {
        for (item in this@mapToMap) {
            this += transform(item)
        }
    }

/**
 * Returns a map from the pairs given by applying the [transform] function to each entry in the
 * original map.
 */
@Generated
inline fun <K, V, A, B> Map<K, V>.mapToMap(
    transform: (entry: Map.Entry<K, V>) -> Pair<A, B>
): Map<A, B> = entries.mapToMap(transform)
