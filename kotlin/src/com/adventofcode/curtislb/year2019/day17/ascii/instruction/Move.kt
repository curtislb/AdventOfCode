package com.adventofcode.curtislb.year2019.day17.ascii.instruction

/**
 * An [Instruction] indicating that the vacuum robot should move forward a number of spaces.
 * @param distance The number of grid units forward that the vacuum robot should be instructed to move.
 */
data class Move(val distance: Int) : Instruction {
    init {
        if (distance < 0) {
            throw IllegalArgumentException("Invalid move distance: $distance")
        }
    }

    override fun toString(): String = distance.toString()
}
