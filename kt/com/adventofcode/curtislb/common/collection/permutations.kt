package com.adventofcode.curtislb.common.collection

/**
 * TODO
 */
fun <T> Collection<T>.permutations(): Sequence<List<T>> {
    return permutationsInternal(this.toSet(), mutableSetOf())
}

/**
 * TODO
 */
private fun <T> permutationsInternal(items: Set<T>, used: MutableSet<T>): Sequence<List<T>> = sequence {
    if (items.size == used.size) {
        yield(listOf())
    } else {
        for (item in items) {
            if (item in used) {
                continue
            }

            used.add(item)
            val itemList = listOf(item)
            for (subPermutation in permutationsInternal(items, used)) {
                yield(itemList + subPermutation)
            }
            used.remove(item)
        }
    }
}
