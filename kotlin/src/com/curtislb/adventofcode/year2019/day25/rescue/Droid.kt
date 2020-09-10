package com.curtislb.adventofcode.year2019.day25.rescue

import com.curtislb.adventofcode.common.intcode.interfaces.ASCII
import com.curtislb.adventofcode.year2019.day25.rescue.command.Command
import java.io.File
import java.lang.StringBuilder

/**
 * A search and rescue droid that can navigate Santa's ship, picking up items along the way. It accepts commands and
 * reports information through an [ASCII]-compatible Intcode program.
 *
 * @param file A file containing the program that controls the droid.
 * @param isInteractive Whether the program should show all ASCII output and echo any provided commands.
 */
class Droid(file: File, private val isInteractive: Boolean = false) {
    /**
     * An ASCII interface to the program that controls the droid.
     */
    private val ascii: ASCII = ASCII(file, showAsciiOutput = isInteractive) { output, isAscii ->
        if (isAscii) {
            when (output) {
                ASCII.NEWLINE_CODE -> {
                    lastLine = lineBuffer.toString()
                    lineBuffer.clear()
                }
                else -> lineBuffer.append(output.toInt().toChar())
            }
        }
    }

    /**
     * A buffer for the current line being produced by the program.
     */
    private val lineBuffer: StringBuilder = StringBuilder()

    /**
     * The most recent line of output produced by the program, or `null` if the program has not produced any lines.
     */
    var lastLine: String? = null
        private set

    /**
     * Whether the droid is waiting to receive a command via [sendCommand].
     */
    val isAwaitingCommand: Boolean get() = ascii.isPaused

    /**
     * Starts running the program so the droid can begin receiving instructions.
     */
    fun start() {
        ascii.run()
    }

    /**
     * Restores the droid to its starting state, immediately after initialization.
     */
    fun reset() {
        ascii.resetState()
    }

    /**
     * Sends a single command to the droid and waits for it to respond.
     */
    fun sendCommand(command: Command) {
        if (isInteractive) {
            println(command)
        }
        ascii.sendInput(command.toString())
        ascii.run()
    }
}
