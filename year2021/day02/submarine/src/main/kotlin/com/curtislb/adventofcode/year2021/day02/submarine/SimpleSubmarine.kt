package com.curtislb.adventofcode.year2021.day02.submarine

import com.curtislb.adventofcode.common.grid.Direction
import com.curtislb.adventofcode.common.grid.Point

/**
 * A submarine that runs commands by moving a given distance in the specified direction.
 *
 * @param initialPosition The starting position of the submarine, before running any commands.
 */
class SimpleSubmarine(initialPosition: Point) : Submarine(initialPosition) {
    /**
     * Increases the horizontal position of the submarine by [value] units.
     */
    override fun forward(value: Int) {
        position = position.move(Direction.RIGHT, value)
    }

    /**
     * Decreases the vertical position (i.e. increases the depth) of the submarine by [value] units.
     */
    override fun down(value: Int) {
        position = position.move(Direction.DOWN, value)
    }

    /**
     * Increases the vertical position (i.e. decreases the depth) of the submarine by [value] units.
     */
    override fun up(value: Int) {
        position = position.move(Direction.UP, value)
    }
}
