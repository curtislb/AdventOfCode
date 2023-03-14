package com.curtislb.adventofcode.common.iteration

/**
 * Returns a [Sequence] of all ordered permutations of the elements in this list.
 */
fun <T> List<T>.permutations(): Sequence<List<T>> =
    if (isEmpty()) {
        emptySequence()
    } else {
        permutationsRecursive(prefix = mutableListOf(), usedIndices = mutableSetOf())
    }

/**
 * Returns a [Sequence] of ordered permutations of this list that begin with the given [prefix] and
 * contain all other elements whose indices are not in [usedIndices].
 */
private fun <T> List<T>.permutationsRecursive(
    prefix: MutableList<T>,
    usedIndices: MutableSet<Int>
): Sequence<List<T>> {
    return sequence {
        if (prefix.size == size) {
            yield(prefix.toList())
        } else {
            forEachIndexed { index, item ->
                if (index !in usedIndices) {
                    prefix.add(item)
                    usedIndices.add(index)
                    yieldAll(permutationsRecursive(prefix, usedIndices))
                    prefix.removeLast()
                    usedIndices.remove(index)
                }
            }
        }
    }
}
