package com.adventofcode.curtislb.year2019.day18.vault.search

import com.adventofcode.curtislb.year2019.day18.vault.space.DoorSpace
import com.adventofcode.curtislb.year2019.day18.vault.space.KeySpace
import com.adventofcode.curtislb.year2019.day18.vault.space.Space

/**
 * Information about the path to a given key location that can be used to determine its weight in the search graph.
 * @param distance The total distance of the path, in number of steps.
 * @param doorsAndKeys A [List] of [DoorSpace] and [KeySpace] spaces, in the order they appear along the path.
 */
data class SearchEdge(val distance: Int, val doorsAndKeys: List<Space>) {
    /**
     * Checks if the path corresponding to this [SearchEdge] is traversable with a given starting collection of keys.
     * @param heldKeys A [KeyCollection] containing all keys that have been collected before traversing the path.
     * @return `true` if a searcher with [heldKeys] can traverse the path corresponding to this [SearchEdge] without
     *  being blocked by a door, or `false` otherwise.
     */
    fun isTraversable(heldKeys: KeyCollection): Boolean {
        val additionalKeys = KeyCollection()
        for (i in 0 until doorsAndKeys.lastIndex) {
            when(val space = doorsAndKeys[i]) {
                is KeySpace -> additionalKeys.withKey(space.symbol)
                is DoorSpace -> {
                    val key = space.keySymbol
                    if (key !in heldKeys && key !in additionalKeys) {
                        return false
                    }
                }
            }
        }
        return true
    }

    companion object {
        /**
         * Creates a [SearchEdge] from a given path through the vault.
         * @param path A [List] of adjacent [Space] types that form a path through part of the vault.
         * @return The [SearchEdge] corresponding to [path].
         */
        fun from(path: List<Space>): SearchEdge {
            return SearchEdge(path.size, path.filter { it is DoorSpace || it is KeySpace })
        }
    }
}
