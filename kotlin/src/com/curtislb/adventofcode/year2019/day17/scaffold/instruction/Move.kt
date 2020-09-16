package com.curtislb.adventofcode.year2019.day17.scaffold.instruction

/**
 * An instruction indicating that the vacuum robot should move forward a given [distance].
 *
 * @throws IllegalArgumentException If [distance] is negative.
 */
data class Move(val distance: Int) : Instruction {
    init {
        require(distance >= 0) { "Distance must be non-negative: $distance" }
    }

    override fun toString(): String = distance.toString()
}
