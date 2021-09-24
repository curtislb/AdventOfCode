package com.curtislb.adventofcode.year2019.day25.rescue.command

/**
 * A command indicating that the rescue droid should drop an [item] it is holding in its current location.
 */
class Drop(private val item: String) : Command {
    override fun toString(): String = "drop $item"
}
