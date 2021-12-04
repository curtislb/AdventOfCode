package com.curtislb.adventofcode.year2021.day02.submarine

/**
 * A submarine that keeps track of a vertical "aim" value, which influences its forward movement.
 *
 * @param initialPosition The horizontal position of the submarine before running any commands.
 * @param initialDepth The depth of the submarine before running any commands.
 * @param initialAim The vertical "aim" value of the submarine before running any commands.
 */
class AimingSubmarine(
    initialPosition: Int,
    initialDepth: Int,
    initialAim: Int = 0
) : Submarine(initialPosition, initialDepth) {

    /**
     * The current vertical "aim" of the submarine.
     */
    var aim: Int = initialAim
        private set

    /**
     * Moves the submarine according to the following rules:
     *
     * - Increases its horizontal position by [value] units.
     * - Increases its depth by [aim] * [value] units.
     */
    override fun forward(value: Int) {
        horizontalPosition += value
        depth += aim * value
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
