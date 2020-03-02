package com.curtislb.adventofcode.year2019.day25.rescue

import com.curtislb.adventofcode.common.intcode.interfaces.ASCII
import com.curtislb.adventofcode.year2019.day25.rescue.command.Command
import java.io.File

/**
 * A search and rescue droid that can navigate Santa's ship, picking up items along the way. It accepts commands and
 * reports information through an [ASCII]-compatible Intcode program.
 *
 * @param file A file containing the program that controls the droid.
 */
class Droid(file: File) {
    /**
     * An ASCII interface to the program that controls the droid.
     */
    private val ascii: ASCII = ASCII(file, showAsciiOutput = true)

    /**
     * Whether the droid is waiting to receive a command via [sendCommand].
     */
    val isAwaitingCommand: Boolean
        get() = ascii.isPaused

    /**
     * Starts running the program so the droid can begin receiving instructions.
     */
    fun start() { ascii.run() }

    /**
     * Restores the droid to its starting state, immediately after initialization.
     */
    fun reset() { ascii.reset() }

    /**
     * Sends a single command to the droid and waits for it to respond.
     */
    fun sendCommand(command: Command) {
        println(command)
        ascii.sendInput(command.toString())
        ascii.run()
    }
}
