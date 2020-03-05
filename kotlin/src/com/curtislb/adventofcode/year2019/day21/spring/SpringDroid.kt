package com.curtislb.adventofcode.year2019.day21.spring

import com.curtislb.adventofcode.common.intcode.interfaces.ASCII
import java.io.File
import java.math.BigInteger

/**
 * A springdroid that navigates and jumps over holes in the ship's hull. It accepts springscript instructions and
 * reports information through an [ASCII]-compatible Intcode program.
 *
 * @param file A file containing the program that controls the springdroid.
 * @param maxInstructions The maximum number of springscript instructions that the springdroid can remember, or `null`
 *  if the springdroid can remember any number of instructions.
 */
class SpringDroid(file: File, private val maxInstructions: Int? = null) {
    /**
     * An ASCII interface to the program that controls the springdroid.
     */
    private val ascii: ASCII = ASCII(file)

    /**
     * Restores the springdroid to its starting state, immediately after initialization.
     */
    fun reset() {
        ascii.reset()
    }

    /**
     * Returns the hull damage reported the springdroid after following the given [springscript] program, or `null` if
     * the springdroid fell into a hole.
     *
     * @throws IllegalArgumentException If the [springscript] program has too many instructions for the springdroid.
     */
    fun runProgram(springscript: SpringScript): BigInteger? {
        // Check that the program doesn't have too many instructions.
        val instructionCount = springscript.instructions.size
        if (maxInstructions != null && instructionCount > maxInstructions) {
            throw IllegalArgumentException("Too many springscript instructions: $instructionCount > $maxInstructions")
        }

        // Send the springscript program to the springdroid.
        var hullDamage: BigInteger? = null
        ascii.processOutput = { output, isAscii ->
            if (!isAscii) {
                hullDamage = output
            }
        }
        ascii.sendInput(springscript.toString())
        ascii.run()
        return hullDamage
    }
}
