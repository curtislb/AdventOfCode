package com.adventofcode.curtislb.common.collection

/**
 * Produces all ordered permutations of a collection of items.
 * @receiver A collection of unique items from which to generate permutations.
 * @return A finite [Sequence] of all possible permutations of the items in [this].
 */
fun <T> Collection<T>.permutations(): Sequence<List<T>> {
    return permutationsInternal(mutableListOf(), mutableSetOf())
}

/**
 * Recursive helper function for [permutations].
 * @receiver A collection of unique items from which to generate permutations.
 * @param prefix A list of items from this collection that will be used to generate future permutations.
 * @param used All items from this collection that have already been included in [prefix].
 * @return A [Sequence] of all possible permutations of items in this collection starting with [prefix].
 */
private fun <T> Collection<T>.permutationsInternal(
    prefix: MutableList<T>,
    used: MutableSet<T>
): Sequence<List<T>> = sequence {
    if (prefix.size == size) {
        yield(prefix)
    } else {
        forEach { item ->
            if (item !in used) {
                used.add(item)
                prefix.add(item)
                yieldAll(permutationsInternal(prefix, used))
                prefix.removeLast()
                used.remove(item)
            }
        }
    }
}
