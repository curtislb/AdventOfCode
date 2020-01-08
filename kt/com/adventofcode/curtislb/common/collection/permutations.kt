package com.adventofcode.curtislb.common.collection

/**
 * Produces all ordered permutations of a collection of items.
 * @receiver A collection of unique items from which to generate permutations.
 * @return A finite [Sequence] of all possible permutations of the items in [this].
 */
fun <T> Collection<T>.permutations(): Sequence<List<T>> {
    return permutationsInternal(this, mutableListOf(), mutableSetOf())
}

/**
 * Recursive helper function for [permutations].
 * @param items A collection of unique items from which to generate permutations.
 * @param prefix A list of values from [items] that will be used to generate future permutations.
 * @param used All values from [items] which have already been included in [prefix].
 * @return A [Sequence] of all possible permutations of [items] starting with [prefix].
 */
private fun <T> permutationsInternal(
    items: Collection<T>,
    prefix: MutableList<T>,
    used: MutableSet<T>
): Sequence<List<T>> = sequence {
    if (prefix.size == items.size) {
        yield(prefix)
    } else {
        items.forEach { item ->
            if (item !in used) {
                used.add(item)
                prefix.add(item)
                yieldAll(permutationsInternal(items, prefix, used))
                prefix.removeAt(prefix.size - 1)
                used.remove(item)
            }
        }
    }
}
