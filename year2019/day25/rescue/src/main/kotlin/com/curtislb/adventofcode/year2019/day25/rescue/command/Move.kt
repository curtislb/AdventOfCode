package com.curtislb.adventofcode.year2019.day25.rescue.command

import com.curtislb.adventofcode.common.grid.Direction

/**
 * A command that makes the rescue droid move in the given [direction] (if possible).
 *
 * Note that [direction] is translated to an equivalent compass direction, with [Direction.UP]
 * corresponding to north.
 */
class Move(private val direction: Direction) : Command {
    override fun toString(): String = when (direction) {
        Direction.UP -> "north"
        Direction.RIGHT -> "east"
        Direction.DOWN -> "south"
        Direction.LEFT -> "west"
    }
}
