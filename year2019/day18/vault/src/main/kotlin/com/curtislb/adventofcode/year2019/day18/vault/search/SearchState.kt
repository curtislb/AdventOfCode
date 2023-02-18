package com.curtislb.adventofcode.year2019.day18.vault.search

import com.curtislb.adventofcode.common.geometry.Point

/**
 * An intermediate state that may be reached while collecting keys from the vault.
 *
 * @param positions The current positions of all searchers in the vault.
 * @param heldKeys A collection of keys currently held by all searchers.
 */
data class SearchState(val positions: List<Point>, val heldKeys: KeyCollection)
