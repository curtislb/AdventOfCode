package com.curtislb.adventofcode.common.collection

/**
 * A map-like object that keeps track of the integer quantity (either positive or negative) of various items.
 *
 * @param initialCounts A map of initial item counts with which to populate the counter.
 */
class Counter<T>(initialCounts: Map<T, Long> = mutableMapOf()) {
    /**
     * A map that holds the nonzero item counts for this counter.
     */
    private val counts: MutableMap<T, Long> = initialCounts.toMutableMap()

    /**
     * All `(key, count)` entries in this counter for which `count != 0`.
     */
    val entriesWithNonzeroCount: Set<Map.Entry<T, Long>> get() = counts.entries

    /**
     * All `(key, count)` entries in this counter for which `count > 0`.
     */
    val entriesWithPositiveCount: Set<Map.Entry<T, Long>>
        get() = counts.entries.filter { (_, count) -> count > 0 }.toSet()

    /**
     * All keys stored in this counter with a nonzero count.
     */
    val keysWithNonzeroCount: Set<T> get() = counts.keys

    /**
     * All keys stored in this counter with a count greater than 0.
     */
    val keysWithPositiveCount: Set<T>
        get() = counts.entries.filter { (_, count) -> count > 0 }.map { (key, _) -> key }.toSet()

    /**
     * Returns `true` if [key] is stored with a nonzero count in this counter, or `false` otherwise.
     */
    operator fun contains(key: T): Boolean = key in counts

    /**
     * Returns the count stored for [key] in this counter. Defaults to 0 if [key] is not stored in the counter.
     */
    operator fun get(key: T): Long = counts.getOrDefault(key, 0L)

    /**
     * Updates the [count] stored for [key] in this counter. If [count] is 0, [key] is removed from the counter.
     */
    operator fun set(key: T, count: Long) {
        if (count == 0L) {
            counts.remove(key)
        } else {
            counts[key] = count
        }
    }

    /**
     * Updates the [count] stored for [key] in this counter. If [count] is 0, [key] is removed from the counter.
     */
    operator fun set(key: T, count: Int) {
        counts[key] = count.toLong()
    }

    /**
     * Returns a map containing all `(key, count)` entries in this counter for which `count` is nonzero.
     */
    fun toMap(): Map<T, Long> = counts.toMap()

    /**
     * Removes all items with negative counts from this counter, resetting their effective counts to 0.
     */
    fun clearNegativeCounts() {
        counts.entries.filter { (_, count) -> count < 0 }.forEach { (key, _) -> counts.remove(key) }
    }
}
