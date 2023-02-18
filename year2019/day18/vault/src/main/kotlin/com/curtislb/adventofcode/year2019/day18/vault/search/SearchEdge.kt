package com.curtislb.adventofcode.year2019.day18.vault.search

import com.curtislb.adventofcode.year2019.day18.vault.space.DoorSpace
import com.curtislb.adventofcode.year2019.day18.vault.space.KeySpace
import com.curtislb.adventofcode.year2019.day18.vault.space.Space

/**
 * Information about the path to a given key location that can be used to determine its weight in
 * the search graph.
 *
 * @param distance The total distance of the path, in number of steps.
 * @param doorsAndKeys A list of [DoorSpace] and [KeySpace] spaces along the path, in the order they
 *  appear.
 */
data class SearchEdge(val distance: Int, val doorsAndKeys: List<Space>) {
    /**
     * Returns `true` if the path for this edge can be traversed with a collection of [heldKeys], or
     * `false` otherwise.
     */
    fun isTraversable(heldKeys: KeyCollection): Boolean {
        val additionalKeys = KeyCollection()
        for (i in 0 until doorsAndKeys.lastIndex) {
            when (val space = doorsAndKeys[i]) {
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
         * Returns a [SearchEdge] representing the given [path] of spaces through the vault.
         */
        fun fromPath(path: List<Space>): SearchEdge {
            return SearchEdge(path.size, path.filter { it is DoorSpace || it is KeySpace })
        }
    }
}
