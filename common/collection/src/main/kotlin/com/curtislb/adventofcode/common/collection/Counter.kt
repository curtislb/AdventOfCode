package com.curtislb.adventofcode.common.collection

/**
 * A map-like object that tracks the counts (both positive and negative) of various items.
 *
 * @param E The key type, representing the type of items counted by the counter.
 *
 * @constructor Creates a new instance of [Counter] with a count of 0 for all keys.
 */
class Counter<E> {
    /**
     * A map that holds the nonzero key counts for the counter.
     */
    private val counts: MutableMap<E, Long> = mutableMapOf()

    /**
     * All `(key, count)` entries in the counter for which `count != 0`.
     */
    val entries: Set<Map.Entry<E, Long>>
        get() = counts.entries

    /**
     * All keys stored in the counter with a nonzero count.
     */
    val keys: Set<E>
        get() = counts.keys

    /**
     * Returns `true` if [key] is stored in the counter with a nonzero count.
     */
    operator fun contains(key: E): Boolean = key in counts

    /**
     * Returns `true` if the count for each key in the counter is greater than or equal to the
     * corresponding count in [other].
     *
     * Any key not stored in a counter is treated as having a count of 0 in that counter.
     */
    operator fun contains(other: Counter<E>): Boolean =
        (keys union other.keys).all { this[it] >= other[it] }

    /**
     * Returns the count stored for [key] in the counter.
     *
     * If [key] is not stored in the counter, this function instead returns 0.
     */
    operator fun get(key: E): Long = counts.getOrDefault(key, 0L)

    /**
     * Updates the [count] stored for [key] in the counter.
     *
     * If [count] is 0, this function instead removes [key] from the counter (if present).
     */
    operator fun set(key: E, count: Long) {
        if (count == 0L) {
            counts.remove(key)
        } else {
            counts[key] = count
        }
    }

    /**
     * Counts the given [keys] and adds the resulting counts to the counter.
     */
    fun addAll(keys: Iterable<E>) {
        for (key in keys) {
            this[key]++
        }
    }

    /**
     * Adds each of the counts in [other] to the counter.
     */
    fun addCounts(other: Counter<E>) {
        for ((key, count) in other.entries) {
            this[key] += count
        }
    }

    /**
     * Removes all keys with nonzero counts from the counter, resetting their counts to 0.
     */
    fun clear() {
        counts.clear()
    }

    /**
     * Removes all keys with negative counts from the counter, resetting their counts to 0.
     */
    fun clearNegative() {
        counts.entries.filter { it.value < 0L }.forEach { counts.remove(it.key) }
    }

    /**
     * Returns all `(key, count)` entries in the counter for which `count > 0`.
     */
    fun positiveEntries(): List<Map.Entry<E, Long>> = counts.entries.filter { it.value > 0L }

    /**
     * Returns all keys with positive counts in the counter.
     */
    fun positiveKeys(): List<E> = counts.entries.filter { it.value > 0L }.map { it.key }

    /**
     * Returns a map containing all `(key, count)` entries in the counter for which `count != 0`.
     */
    fun toMap(): Map<E, Long> = counts.toMap()

    override fun toString() = counts.toString()
}
