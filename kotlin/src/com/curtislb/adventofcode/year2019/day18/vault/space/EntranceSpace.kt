package com.curtislb.adventofcode.year2019.day18.vault.space

/**
 * A space representing an entrance to the vault, where a searcher begins when looking for keys.
 */
object EntranceSpace : Space {
    override val symbol: Char = '@'
    override val isOccupiable: Boolean = true
}
