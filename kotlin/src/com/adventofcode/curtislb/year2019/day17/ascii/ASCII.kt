package com.adventofcode.curtislb.year2019.day17.ascii

import com.adventofcode.curtislb.common.intcode.Intcode
import java.io.File
import java.math.BigInteger

/**
 * An implementation of the Aft Scaffolding Control and Information Interface (ASCII) for operating the vacuum robot.
 * @param file A [File] containing the [Intcode] program that operates the interface.
 * @param showAllOutput Whether all ASCII output from the [Intcode] program should be displayed.
 */
class ASCII(file: File, private val showAllOutput: Boolean = false) {
    /**
     * The [Intcode] computer used to run the interface.
     */
    private val intcode: Intcode = Intcode(file)

    /**
     * The [ScaffoldGrid] layout provided by the interface.
     */
    var grid: ScaffoldGrid = ScaffoldGrid()
        private set

    /**
     * Whether the last output from [intcode] was a newline. Used to construct the initial [grid].
     */
    private var isPrevOutputNewline = false

    init {
        intcode.onOutput = { handleOutput(it) }
        intcode[0] = BigInteger.TWO
        intcode.run()
    }

    /**
     * Instructs the vacuum robot to move according to a given movement routine.
     * @param routine A [Routine] representing the movement instructions to be given to the robot.
     * @param showFeed Whether a live camera feed should be shown during the robot's movement.
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
     * Converts a given input to ASCII and sends it to the [intcode] program, followed by a newline.
     * @param input A [String] to be converted to a sequence of ASCII codes and sent as input to [intcode].
     */
    private fun sendInput(input: String) {
        intcode.sendInput(input.map { it.toInt().toBigInteger() }.asSequence())
        intcode.sendInput(NEWLINE_CODE)
    }

    /**
     * Handles the output of [intcode], using it to construct the [grid] and display relevant output to the user.
     *
     * Note that because [Intcode.onOutput] may change during execution, this function should be set again for [intcode]
     * each time it is reset after initialization.
     *
     * @param output A [BigInteger] output value produced by [intcode].
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
     * Displays any relevant output from [intcode] to the user.
     *
     * Specifically, this function prints any output from [intcode] that exceeds [MAX_CHAR_CODE] and optionally prints
     * the [Char] corresponding to any ASCII value output if [showAllOutput] is `true`.
     *
     * @param output A [BigInteger] output value produced by [intcode].
     */
    private fun displayOutput(output: BigInteger) {
        when {
            output > MAX_CHAR_CODE -> println(output)
            showAllOutput -> print(output.toInt().toChar())
        }
    }

    private companion object {
        /**
         * A [BigInteger] representing the maximum value of a printable ASCII character.
         */
        val MAX_CHAR_CODE: BigInteger = BigInteger("127")

        /**
         * A [BigInteger] representing the ASCII value of the newline character.
         */
        val NEWLINE_CODE: BigInteger = BigInteger.TEN
    }
}
