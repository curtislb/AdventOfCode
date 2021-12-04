package com.curtislb.adventofcode.year2021.day02.submarine

/**
 * A submarine that runs commands by moving a given distance in the specified direction.
 *
 * @param initialPosition The horizontal position of the submarine before running any commands.
 * @param initialDepth The depth of the submarine before running any commands.
 */
class SimpleSubmarine(
    initialPosition: Int,
    initialDepth: Int
) : Submarine(initialPosition, initialDepth) {

    /**
     * Increases the horizontal position of the submarine by [value] units.
     */
    override fun forward(value: Int) {
        horizontalPosition += value
    }

    /**
     * Increases the depth of the submarine by [value] units.
     */
    override fun down(value: Int) {
        depth += value
    }

    /**
     * Decreases the depth of the submarine by [value] units.
     */
    override fun up(value: Int) {
        depth -= value
    }
}
