package com.curtislb.adventofcode.year2019.day25.rescue.command

/**
 * A command indicating that the rescue droid should list the items it is currently holding.
 */
object Inventory : Command {
    override fun toString(): String = "inv"
}
