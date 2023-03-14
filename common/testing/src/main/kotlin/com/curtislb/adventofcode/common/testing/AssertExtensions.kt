package com.curtislb.adventofcode.common.testing

import org.assertj.core.api.AbstractDoubleAssert
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.offset
import org.assertj.core.api.ListAssert

/**
 * Verifies that the list contains exactly the given [pairs] and nothing else, **in any order**.
 *
 * Each pair in [pairs] is treated as unordered. In other words, the list is considered to contain a
 * given `pair` if either it or its reverse, `Pair(pair.second, pair.first)`, is present.
 */
fun <T> ListAssert<Pair<T, T>>.containsExactlyInAnyOrderUnorderedPairs(
    vararg pairs: Pair<T, T>
): ListAssert<Pair<T, T>> {
    val assertWithSize = hasSize(pairs.size)
    return pairs.fold(assertWithSize) { partialAssert, pair ->
        partialAssert.satisfiesAnyOf(
            { assertThat(it).contains(pair) },
            { assertThat(it).contains(Pair(pair.second, pair.first)) }
        )
    }
}

/**
 * Verifies that the value is approximately equal to the given [expected] value (+/- [tolerance]).
 */
fun AbstractDoubleAssert<*>.isApproximately(
    expected: Double,
    tolerance: Double = 0.000001
): AbstractDoubleAssert<*> {
    return isCloseTo(expected, offset(tolerance))
}
