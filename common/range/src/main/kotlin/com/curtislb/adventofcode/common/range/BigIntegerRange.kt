package com.curtislb.adventofcode.common.range

import java.math.BigInteger
import java.util.Objects

/**
 * An ordered, contiguous range of [BigInteger] values.
 *
 * @property first The minimum value in the range.
 * @property last The maximum value in the range (inclusive).
 *
 * @constructor Creates a new instance of [BigIntegerRange] with the given [first] and [last]
 *  values.
 */
class BigIntegerRange(
    val first: BigInteger,
    val last: BigInteger
) : ClosedRange<BigInteger>, Iterable<BigInteger> {

    override val start: BigInteger
        get() = first

    override val endInclusive: BigInteger
        get() = last

    /**
     * Creates a new instance of [BigIntegerRange] with the given [first] and [last] values.
     */
    constructor(first: Long, last: Long) : this(first.toBigInteger(), last.toBigInteger())

    override fun toString(): String = "BigIntegerRange($first, $last)"

    override fun equals(other: Any?): Boolean = when {
        other !is BigIntegerRange -> false
        isEmpty() && other.isEmpty() -> true
        else -> first == other.first && last == other.last
    }

    override fun hashCode(): Int = if (isEmpty()) -1 else Objects.hash(first, last)

    override fun iterator(): Iterator<BigInteger> = object : Iterator<BigInteger> {
        private var current: BigInteger = first

        override fun hasNext(): Boolean = current <= last

        override fun next(): BigInteger = current++
    }

    companion object {
        /**
         * An empty range of values of type [BigInteger].
         */
        val EMPTY: BigIntegerRange = BigIntegerRange(BigInteger.ONE, BigInteger.ZERO)
    }
}

/**
 * Returns a [BigIntegerRange] with the same integer values as this range.
 */
fun IntRange.toBigIntegerRange(): BigIntegerRange = BigIntegerRange(first.toLong(), last.toLong())

/**
 * Returns a [BigIntegerRange] with the same integer values as this range.
 */
fun LongRange.toBigIntegerRange(): BigIntegerRange = BigIntegerRange(first, last)
