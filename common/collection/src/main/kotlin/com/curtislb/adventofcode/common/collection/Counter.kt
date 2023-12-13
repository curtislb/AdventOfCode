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
    private val countMap: MutableMap<E, Long> = mutableMapOf()

    /**
     * All nonzero counts stored in the counter. This includes duplicates if the same count is
     * stored for multiple keys.
     */
    val counts: Collection<Long>
        get() = countMap.values

    /**
     * All `(key, count)` entries in the counter for which `count != 0`.
     */
    val entries: Set<Map.Entry<E, Long>>
        get() = countMap.entries

    /**
     * All keys stored in the counter with a nonzero count.
     */
    val keys: Set<E>
        get() = countMap.keys

    /**
     * Returns `true` if [key] is stored in the counter with a nonzero count.
     */
    operator fun contains(key: E): Boolean = key in countMap

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
    operator fun get(key: E): Long = countMap.getOrDefault(key, 0L)

    /**
     * Updates the [count] stored for [key] in the counter.
     *
     * If [count] is 0, this function instead removes [key] from the counter (if present).
     */
    operator fun set(key: E, count: Long) {
        if (count == 0L) {
            countMap.remove(key)
        } else {
            countMap[key] = count
        }
    }

    /**
     * Counts the given [items] and adds the resulting counts to the counter.
     */
    fun addAll(items: Iterable<E>) {
        for (key in items) {
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
        countMap.clear()
    }

    /**
     * Removes all keys with negative counts from the counter, resetting their counts to 0.
     */
    fun clearNegative() {
        countMap.entries.filter { it.value < 0L }.forEach { countMap.remove(it.key) }
    }

    /**
     * Returns all positive counts in the counter. This includes duplicates if the same count is
     * stored for multiple keys.
     */
    fun positiveCounts(): List<Long> = countMap.values.filter { it > 0L }

    /**
     * Returns all `(key, count)` entries in the counter for which `count > 0`.
     */
    fun positiveEntries(): List<Map.Entry<E, Long>> = countMap.entries.filter { it.value > 0L }

    /**
     * Returns all keys with positive counts in the counter.
     */
    fun positiveKeys(): List<E> = countMap.entries.filter { it.value > 0L }.map { it.key }

    /**
     * Updates the count for each key to match its count in [other], if the latter count is greater.
     */
    fun takeLarger(other: Counter<E>) {
        val allKeys = keys union other.keys
        for (key in allKeys) {
            this[key] = maxOf(this[key], other[key])
        }
    }

    /**
     * Returns a map containing all `(key, count)` entries in the counter for which `count != 0`.
     */
    fun toMap(): Map<E, Long> = countMap.toMap()

    override fun toString() = countMap.toString()

    companion object {
        /**
         * Returns a [Counter] initialized by counting the given [items].
         */
        fun <E> fromItems(items: Iterable<E>) = Counter<E>().apply { addAll(items) }

        /**
         * Returns a [Counter] initialized with `(key, count)` entries from the given [map].
         */
        fun <E> fromMap(map: Map<E, Long>) = Counter<E>().apply {
            for ((key, count) in map.entries) {
                this[key] = count
            }
        }
    }
}
