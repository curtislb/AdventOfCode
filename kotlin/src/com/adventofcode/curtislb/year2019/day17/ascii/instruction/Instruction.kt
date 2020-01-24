package com.adventofcode.curtislb.year2019.day17.ascii.instruction

/**
 * A single instruction that may be given to the vacuum robot.
 */
interface Instruction {
    /**
     * The [String] for this [Instruction], which can be provided to the vacuum robot as an ASCII command.
     */
    override fun toString(): String
}
