package com.curtislb.adventofcode.year2021.day02.submarine

import com.curtislb.adventofcode.common.grid.Point

/**
 * A submarine with a horizontal and vertical position that is capable of processing commands.
 *
 * @param initialPosition The starting position of the submarine, before running any commands.
 */
abstract class Submarine(initialPosition: Point) {
    /**
     * The current position of the submarine.
     *
     * Note that a negative value of [Point.y] corresponds to a positive depth for the submarine.
     */
    var position: Point = initialPosition
        protected set

    /**
     * Runs the "forward" command with the given [value] as an argument.
     */
    abstract fun forward(value: Int)

    /**
     * Runs the "down" command with the given [value] as an argument.
     */
    abstract fun down(value: Int)

    /**
     * Runs the "up" command with the given [value] as an argument.
     */
    abstract fun up(value: Int)

    /**
     * Runs the command represented by the given [command] string. This command may (but need not)
     * update the position of the submarine.
     *
     * The supported commands are as follows:
     *
     * - `"forward $argument"`: Runs the [forward] command with the given integer `argument`.
     * - `"down $argument"`: Runs the [down] command with the given integer `argument`.
     * - `"up $argument"`: Runs the [forward] command, with the given integer `argument`.
     *
     * @throws IllegalArgumentException If [command] does not represent a valid command.
     */
    fun runCommand(command: String) {
        val matchResult = COMMAND_REGEX.matchEntire(command)
        require(matchResult != null) { "Malformed command string: $command" }

        val (commandName, argument) = matchResult.destructured
        when (commandName) {
            "forward" -> forward(argument.toInt())
            "down" -> down(argument.toInt())
            "up" -> up(argument.toInt())
            else -> throw IllegalArgumentException("Unrecognized command: $commandName")
        }
    }

    companion object {
        /**
         * The regex to be matched against command strings for [runCommand].
         */
        private val COMMAND_REGEX = """\s*([a-z]+)\s+(\d+)\s*""".toRegex()
    }
}
