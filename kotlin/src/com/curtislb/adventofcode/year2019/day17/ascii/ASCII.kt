package com.curtislb.adventofcode.year2019.day17.ascii

import com.curtislb.adventofcode.common.intcode.Intcode
import java.io.File
import java.math.BigInteger

/**
 * An implementation of the Aft Scaffolding Control and Information Interface (ASCII) for operating the vacuum robot.
 *
 * @param file A file containing the [Intcode] program that controls the interface.
 * @param showAllOutput Whether all ASCII output from the program should be displayed.
 */
class ASCII(file: File, private val showAllOutput: Boolean = false) {
    /**
     * The [Intcode] program that controls the interface.
     */
    private val intcode: Intcode = Intcode(file)

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
        intcode.onOutput = { handleOutput(it) }
        intcode[0] = BigInteger.TWO
        intcode.run()
    }

    /**
     * Moves the vacuum robot according to [routine], printing intermediate states of the grid if [showFeed] is `true`.
     */
    fun moveRobot(routine: Routine, showFeed: Boolean = false) {
        sendInput(routine.toString())
        sendInput(if (showFeed) "y" else "n")
        intcode.run()
    }

    /**
     * Restores the interface to its starting state, immediately after initialization.
     */
    fun reset() {
        grid = ScaffoldGrid()
        isPrevOutputNewline = false
        intcode.reset()
        intcode.onOutput = { handleOutput(it) }
        intcode[0] = BigInteger.TWO
        intcode.run()
    }

    /**
     * Converts [input] to an ASCII sequence and sends it as input to the interface, followed by a newline.
     */
    private fun sendInput(input: String) {
        intcode.sendInput(input.map { it.toInt().toBigInteger() }.asSequence())
        intcode.sendInput(NEWLINE_CODE)
    }

    /**
     * Handles [output] from the interface, using it to construct the grid and display relevant output to the user.
     *
     * Because this method may change [Intcode.onOutput] during execution, it should be set again each time [intcode] is
     * reset.
     */
    private fun handleOutput(output: BigInteger) {
        displayOutput(output)
        when (output) {
            NEWLINE_CODE -> {
                if (isPrevOutputNewline) {
                    grid.removeRow()
                    intcode.onOutput = { displayOutput(it) }
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

    /**
     * Prints [output] if it exceeds [MAX_CHAR_CODE], or the ASCII character for [output] if [showAllOutput] is `true`.
     */
    private fun displayOutput(output: BigInteger) {
        when {
            output > MAX_CHAR_CODE -> println(output)
            showAllOutput -> print(output.toInt().toChar())
        }
    }

    private companion object {
        /**
         * The maximum value of a printable ASCII character.
         */
        val MAX_CHAR_CODE: BigInteger = BigInteger("127")

        /**
         * The ASCII value of the newline character.
         */
        val NEWLINE_CODE: BigInteger = BigInteger.TEN
    }
}
