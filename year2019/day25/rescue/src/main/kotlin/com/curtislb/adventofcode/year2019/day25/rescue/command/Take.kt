package com.curtislb.adventofcode.year2019.day25.rescue.command

/**
 * A command that makes the rescue droid take an [item] from its current location (if possible).
 */
class Take(private val item: String) : Command {
    override fun toString(): String = "take $item"
}
