package com.curtislb.adventofcode.year2019.day17.scaffold

import com.curtislb.adventofcode.common.intcode.interfaces.ASCII
import java.io.File
import java.math.BigInteger

/**
 * A vacuum robot that navigates and cleans debris from the ship's scaffold. It accepts movement instructions and
 * reports information through an [ASCII]-compatible Intcode program.
 *
 * @param file A file containing the program that controls the robot.
 */
class Robot(file: File) {
    /**
     * An ASCII interface to the program that controls the robot.
     */
    private val ascii: ASCII = ASCII(file) { output, _ -> processOutput(output) }

    /**
     * The initial layout of the scaffold grid, including the location of the vacuum robot.
     */
    var grid: ScaffoldGrid = ScaffoldGrid()
        private set

    /**
     * Whether the last program output was a newline. Used to construct the initial grid.
     */
    private var isPrevOutputNewline = false

    init {
        ascii[0] = BigInteger.TWO
        ascii.run()
    }

    /**
     * Returns the dust collected by the vacuum robot after moving according to the given [routine], or `null` if the
     * robot falls into space. If [showFeed] is `true`, the robot will also print the state of the grid as it moves.
     */
    fun moveRobot(routine: Routine, showFeed: Boolean = false): BigInteger? {
        var dustCollected: BigInteger? = null
        ascii.processOutput = { output, isAscii ->
            if (!isAscii) {
                dustCollected = output
            }
        }
        ascii.sendInput(routine.toAsciiInput())
        ascii.sendInput(if (showFeed) "y" else "n")
        ascii.run()
        return dustCollected
    }

    /**
     * Restores the vacuum robot to its starting state, immediately after initialization.
     */
    fun reset() {
        grid = ScaffoldGrid()
        isPrevOutputNewline = false
        ascii.resetState()
        ascii.processOutput = { output, _ -> processOutput(output) }
        ascii[0] = BigInteger.TWO
        ascii.run()
    }

    /**
     * Handles [output] from the interface, using it to construct the grid and display relevant output to the user.
     *
     * Because this method may change [ASCII.processOutput] during execution, it should be set again each time the
     * [ascii] interface is reset.
     */
    private fun processOutput(output: BigInteger) {
        when (output) {
            ASCII.NEWLINE_CODE -> {
                if (isPrevOutputNewline) {
                    grid.removeRow()
                    ascii.processOutput = null
                } else {
                    grid.addRow()
                    isPrevOutputNewline = true
                }
            }
            else -> {
                grid.addSpace(Space.from(output))
                isPrevOutputNewline = false
            }
        }
    }
}
