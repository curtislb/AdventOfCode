package com.curtislb.adventofcode.year2019.day17.ascii.instruction

/**
 * An instruction indicating that the vacuum robot should move forward a given [distance].
 */
data class Move(val distance: Int) : Instruction {
    init {
        if (distance < 0) {
            throw IllegalArgumentException("Invalid move distance: $distance")
        }
    }

    override fun toString(): String = distance.toString()
}
