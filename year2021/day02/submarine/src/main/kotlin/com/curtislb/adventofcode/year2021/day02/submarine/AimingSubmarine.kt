package com.curtislb.adventofcode.year2021.day02.submarine

import com.curtislb.adventofcode.common.grid.Point

/**
 * A submarine that keeps track of a vertical "aim" value, which influences its forward movement.
 *
 * @param initialPosition The starting position of the submarine, before running any commands.
 * @param initialAim The initial "aim" value of the submarine, before running any commands.
 */
class AimingSubmarine(initialPosition: Point, initialAim: Int = 0) : Submarine(initialPosition) {
    /**
     * The current vertical "aim" of the submarine.
     */
    var aim: Int = initialAim
        private set

    /**
     * Moves the submarine according to the following rules:
     *
     * - Increases its horizontal position by [value] units.
     * - Decreases its vertical position (i.e. increases its depth) by [aim] * [value] units.
     */
    override fun forward(value: Int) {
        position = Point(position.x + value, position.y - (aim * value))
    }

    /**
     * Increases the [aim] of the submarine by the given [value].
     */
    override fun down(value: Int) {
        aim += value
    }

    /**
     * Decreases the [aim] of the submarine by the given [value].
     */
    override fun up(value: Int) {
        aim -= value
    }
}
