package com.curtislb.adventofcode.year2019.day25.rescue.command

/**
 * A command that makes the rescue droid list the items that it's currently holding.
 */
object Inventory : Command {
    override fun toString(): String = "inv"
}
