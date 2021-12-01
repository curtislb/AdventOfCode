package com.curtislb.adventofcode.common.collection

import lombok.Generated

/**
 * Returns the number of adjacent element pairs in this list that satisfy the given [predicate].
 */
@Generated
inline fun <T> List<T>.countPairwise(predicate: (firstItem: T, secondItem: T) -> Boolean): Int =
    (0 until lastIndex).count { predicate(this[it], this[it + 1]) }
