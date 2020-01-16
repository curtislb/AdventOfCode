package com.adventofcode.curtislb.common.collection

/**
 * A map-like object that keeps track of the integer quantity (either positive or negative) of various items.
 * @param initialCounts A [Map] of initial item counts with which to populate the [Counter].
 */
class Counter<T>(initialCounts: Map<T, Long> = mutableMapOf()) {
    /**
     * An internal [Map] that holds the nonzero item counts for this [Counter].
     */
    private val counts: MutableMap<T, Long> = initialCounts.toMutableMap()

    /**
     * A [Set] of all `(key, count)` entries in this [Counter] for which `count != 0`.
     */
    val entriesWithNonzeroCount: Set<Map.Entry<T, Long>>
        get() = counts.entries

    /**
     * A [Set] of all `(key, count)` entries in this [Counter] for which `count > 0`.
     */
    val entriesWithPositiveCount: Set<Map.Entry<T, Long>>
        get() = counts.entries.filter { (_, count) -> count > 0 }.toSet()

    /**
     * A [Set] of all item keys in this [Counter] that have a nonzero count.
     */
    val keysWithNonzeroCount: Set<T>
        get() = counts.keys

    /**
     * A [Set] of all item keys in this [Counter] that have a count greater than 0.
     */
    val keysWithPositiveCount: Set<T>
        get() = counts.entries.filter { (_, count) -> count > 0 }.map { (key, _) -> key }.toSet()

    /**
     * Checks if a given item is present with a nonzero count in this [Counter].
     * @param key The item to be checked for in this [Counter].
     * @return `true` if [key] exists with a nonzero count in this [Counter], or `false` otherwise.
     */
    operator fun contains(key: T): Boolean = key in counts

    /**
     * Gets the current count of an item in this [Counter]. Defaults to 0 for new items.
     * @param key The item whose count should be returned.
     * @return The current count stored for [key], if [key] is present in this [Counter], or 0 otherwise.
     */
    operator fun get(key: T): Long = counts.getOrDefault(key, 0L)

    /**
     * Updates the count of an item in this [Counter]. The item is removed if its new count is 0.
     * @param key The item whose count should be set.
     * @param count The new count to be set for [key].
     */
    operator fun set(key: T, count: Long) {
        if (count == 0L) {
            counts.remove(key)
        } else {
            counts[key] = count
        }
    }

    /**
     * Updates the count of an item in this [Counter]. The item is removed if its new count is 0.
     * @param key The item whose count should be set.
     * @param count The new count to be set for [key].
     */
    operator fun set(key: T, count: Int) { counts[key] = count.toLong() }

    /**
     * Creates a read-only [Map] containing all `(key, count)` pairs in this [Counter] for which `count` is nonzero.
     * @return A new [Map] from each shallow-copied item key currently in [Counter] to its corresponding nonzero count.
     */
    fun toMap(): Map<T, Long> = counts.toMap()

    /**
     * Removes all items with negative counts from this [Counter], resetting their counts to 0.
     */
    fun clearNegativeCounts() {
        counts.entries.filter { (_, count) -> count < 0 }.forEach { (key, _) -> counts.remove(key) }
    }
}
