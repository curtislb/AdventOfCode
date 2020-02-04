package com.adventofcode.curtislb.common.collection

/**
 * Produces the [Map] that results from applying a given function to each item in this [Collection].
 * @receiver The [Collection] whose items will be used to build a [Map].
 * @param transform A function that maps each item in this [Collection] to a `(key, value)` [Pair].
 * @return A [Map] containing `(key, value)` [Pair] entries from applying [transform] to all items in this [Collection].
 */
fun <E, K, V> Collection<E>.mapToMap(transform: (item: E) -> Pair<K, V>): Map<K, V> {
    listOf<Any>().map {  }
    val mapBuilder = mutableMapOf<K, V>()
    forEach { item ->
        val (key, value) = transform(item)
        mapBuilder[key] = value
    }
    return mapBuilder
}

/**
 * Produces the [Map] that results from applying a given function to each entry in this [Map].
 * @receiver The [Map] whose entries will be used to build a new [Map].
 * @param transform A function that maps each [Map.Entry] in this [Collection] to a `(key, value)` [Pair].
 * @return A new [Map] containing `(key, value)` [Pair] entries from applying [transform] to all entries in this [Map].
 */
fun <K, V, A, B> Map<K, V>.mapToMap(transform: (entry: Map.Entry<K, V>) -> Pair<A, B>): Map<A, B> {
    return entries.mapToMap(transform)
}
