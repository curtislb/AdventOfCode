package com.curtislb.adventofcode.common.collection

import java.math.BigInteger

/** A map-like object that tracks the counts (both positive and negative) of various items. */
class Counter<E>() {
    /**
     * A map that holds the nonzero key counts for this counter.
     */
    private val counts: MutableMap<E, Long> = mutableMapOf()

    /**
     * All `(key, count)` entries in this counter for which `count != 0`.
     */
    val entries: Set<Map.Entry<E, Long>>
        get() = counts.entries

    /**
     * All keys stored in this counter with a nonzero count.
     */
    val keys: Set<E>
        get() = counts.keys

    /**
     * A map-like object that tracks the counts (both positive and negative) of various items.
     *
     * @param initialCounts A map of initial item counts with which to populate the counter.
     */
    constructor(initialCounts: Map<E, Long>) : this() {
        for ((item, count) in initialCounts) {
            if (count != 0L) {
                this[item] = count
            }
        }
    }

    /**
     * A map-like object that tracks the counts (both positive and negative) of various items.
     *
     * @param items A collection of initial items with which to populate the counter.
     */
    constructor(items: Iterable<E>) : this() {
        addAll(items)
    }

    override fun toString() = counts.toString()

    /**
     * Returns if [key] is stored with a nonzero count in this counter.
     */
    operator fun contains(key: E): Boolean = key in counts

    /**
     * Returns if this counter has a greater or equal count for each key in [other].
     */
    operator fun contains(other: Counter<E>): Boolean =
        other.entries.all { (key, count) -> this[key] >= count }

    /**
     * Returns the count stored for [key] in this counter. Defaults to 0 if [key] is not stored in
     * the counter.
     */
    operator fun get(key: E): Long = counts.getOrDefault(key, 0L)

    /**
     * Updates the [count] stored for [key] in this counter. If [count] is 0, [key] is removed from
     * the counter.
     */
    operator fun set(key: E, count: Long) {
        if (count == 0L) {
            counts.remove(key)
        } else {
            counts[key] = count
        }
    }

    /**
     * Adds each of the counts in [other] to this counter.
     */
    operator fun plusAssign(other: Counter<E>) {
        for ((key, count) in other.entries) {
            this[key] += count
        }
    }

    /**
     * Subtracts each of the counts in [other] from this counter.
     */
    operator fun minusAssign(other: Counter<E>) {
        for ((key, count) in other.entries) {
            this[key] -= count
        }
    }

    /**
     * Counts the given [keys] and adds the resulting counts to this counter.
     */
    fun addAll(keys: Iterable<E>) {
        for (key in keys) {
            this[key]++
        }
    }

    /**
     * Removes all keys with nonzero counts from this counter, resetting their counts to 0.
     */
    fun clearNonzeroCounts() {
        counts.clear()
    }

    /**
     * Removes all keys with negative counts from this counter, resetting their counts to 0.
     */
    fun clearNegativeCounts() {
        counts.entries.filter { it.value < 0L }.forEach { counts.remove(it.key) }
    }

    /**
     * Returns all `(key, count)` entries in this counter for which `count > 0`.
     */
    fun getPositiveCountEntries(): List<Map.Entry<E, Long>> =
        counts.entries.filter { it.value > 0L }

    /**
     *
     */
    fun getPositiveCountKeys(): List<E> = counts.entries.filter { it.value > 0L }.map { it.key }

    /**
     * Returns the sum of the counts for all keys in this counter.
     *
     * If this sum may be outside the range [Long.MIN_VALUE]..[Long.MAX_VALUE], use
     * [sumCountsAsBigInteger] instead.
     */
    fun sumCounts(): Long = counts.values.sum()

    /**
     * Returns the sum of the counts for all keys in this counter as a [BigInteger].
     *
     * If this sum is guaranteed to be in the range [Long.MIN_VALUE]..[Long.MAX_VALUE], [sumCounts]
     * may be used instead.
     */
    fun sumCountsAsBigInteger(): BigInteger = counts.values.sumOf { it.toBigInteger() }

    /**
     * Returns a map containing all `(key, count)` entries in this counter for which `count` is
     * nonzero.
     */
    fun toMap(): Map<E, Long> = counts.toMap()
}
