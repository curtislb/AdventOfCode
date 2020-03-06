package com.curtislb.adventofcode.common.collection

/**
 * Returns all ordered permutations of this collection of items.
 */
fun <T> Collection<T>.permutations(): Sequence<List<T>> {
    return if (isEmpty()) emptySequence() else permutationsInternal(mutableListOf(), mutableSetOf())
}

/**
 * Recursive helper method for [permutations].
 */
private fun <T> Collection<T>.permutationsInternal(
    prefix: MutableList<T>,
    used: MutableSet<T>
): Sequence<List<T>> = sequence {
    if (prefix.size == size) {
        yield(prefix.toList())
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
