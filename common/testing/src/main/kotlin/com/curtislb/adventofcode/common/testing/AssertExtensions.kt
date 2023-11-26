package com.curtislb.adventofcode.common.testing

import org.assertj.core.api.AbstractAssert
import org.assertj.core.api.AbstractDoubleAssert
import org.assertj.core.api.AbstractIterableAssert
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.offset
import org.assertj.core.api.ThrowingConsumer

/**
 * Verifies that the actual group contains exactly the values in any one of the given [iterables]
 * and nothing else, **in any order**.
 */
fun <
    SELF : AbstractIterableAssert<SELF, ACTUAL, ELEMENT, ELEMENT_ASSERT>,
    ACTUAL : Iterable<ELEMENT>,
    ELEMENT,
    ELEMENT_ASSERT : AbstractAssert<ELEMENT_ASSERT, ELEMENT>
> AbstractIterableAssert<
    SELF,
    ACTUAL,
    ELEMENT,
    ELEMENT_ASSERT
>.containsExactlyInAnyOrderElementsOfAny(vararg iterables: Iterable<ELEMENT>): SELF {
    val assertions = Array(iterables.size) { index ->
        ThrowingConsumer<ACTUAL> { containsExactlyInAnyOrderElementsOf(iterables[index]) }
    }
    return satisfiesAnyOf(*assertions)
}

/**
 * Verifies that the actual group contains exactly the given unordered [pairs] and nothing else,
 * **in any order**.
 *
 * Each pair in [pairs] is considered to be unordered. In other words, if a `pair` or its reverse,
 * `Pair(pair.second, pair.first)`, is present in the iterable, the iterable is treated as
 * containing that pair.
 */
fun <
    SELF : AbstractIterableAssert<SELF, ACTUAL, Pair<ELEMENT, ELEMENT>, ELEMENT_ASSERT>,
    ACTUAL : Iterable<Pair<ELEMENT, ELEMENT>>,
    ELEMENT,
    ELEMENT_ASSERT : AbstractAssert<ELEMENT_ASSERT, Pair<ELEMENT, ELEMENT>>
> AbstractIterableAssert<
    SELF,
    ACTUAL,
    Pair<ELEMENT, ELEMENT>,
    ELEMENT_ASSERT
>.containsExactlyInAnyOrderUnorderedPairs(vararg pairs: Pair<ELEMENT, ELEMENT>): SELF {
    val sizeAssertion = hasSize(pairs.size)
    return pairs.fold(sizeAssertion) { acc, pair ->
        acc.satisfiesAnyOf(
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
