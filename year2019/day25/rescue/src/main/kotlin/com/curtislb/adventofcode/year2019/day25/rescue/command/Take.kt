package com.curtislb.adventofcode.year2019.day25.rescue.command

/**
 * A command indicating that the rescue droid should take an [item] from its current location.
 */
class Take(private val item: String) : Command {
    override fun toString(): String = "take $item"
}
