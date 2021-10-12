package com.curtislb.adventofcode.year2019.day17.scaffold.instruction

/**
 * A single instruction that may be given to the vacuum robot.
 */
interface Instruction {
    /**
     * The string for this instruction, which can be given as an ASCII command to the vacuum robot.
     */
    override fun toString(): String
}
