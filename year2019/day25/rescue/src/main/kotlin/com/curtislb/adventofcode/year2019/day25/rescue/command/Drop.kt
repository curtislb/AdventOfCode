package com.curtislb.adventofcode.year2019.day25.rescue.command

/**
 * A command that makes the rescue droid drop a held [item] in its current location (if possible).
 */
class Drop(private val item: String) : Command {
    override fun toString(): String = "drop $item"
}
