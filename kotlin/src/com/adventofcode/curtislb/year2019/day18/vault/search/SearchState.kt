package com.adventofcode.curtislb.year2019.day18.vault.search

import com.adventofcode.curtislb.common.grid.Point

/**
 * An intermediate state that a searcher(s) may reach while traveling the vault to collect keys.
 * @param positions The current [Point] positions of all searchers in the vault.
 * @param heldKeys A [KeyCollection] representing all keys currently held by all searchers.
 */
data class SearchState(val positions: Collection<Point>, val heldKeys: KeyCollection)
