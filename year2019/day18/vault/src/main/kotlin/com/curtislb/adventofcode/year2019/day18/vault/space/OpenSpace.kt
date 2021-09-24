package com.curtislb.adventofcode.year2019.day18.vault.space

/**
 * A space representing an open position, which may be freely passed through.
 */
object OpenSpace : Space {
    override val symbol: Char = '.'
    override val isOccupiable: Boolean = true
}
