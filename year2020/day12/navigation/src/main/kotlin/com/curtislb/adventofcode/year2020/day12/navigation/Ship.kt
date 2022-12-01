package com.curtislb.adventofcode.year2020.day12.navigation

import com.curtislb.adventofcode.common.grid.Direction
import com.curtislb.adventofcode.common.grid.RotationAngle

/**
 * A ship in a 2D grid that is capable of processing navigation instructions.
 */
interface Ship {
    /**
     * Processes the instruction to move a given [distance] in a [direction].
     */
    fun move(direction: Direction, distance: Int = 1)

    /**
     * Processes the instruction to turn left (counterclockwise) by the given [angle].
     */
    fun turnLeft(angle: RotationAngle = RotationAngle.DEGREES_90)

    /**
     * Processes the instruction to turn right (clockwise) by the given [angle].
     */
    fun turnRight(angle: RotationAngle = RotationAngle.DEGREES_90) {
        turnLeft(-angle)
    }

    /**
     * Processes the instruction to go forward a number of times equal to [count].
     */
    fun goForward(count: Int = 1)

    /**
     * Processes the [instruction] represented by a string of the form `"${action}${value}"`, where
     * `action` is a single uppercase letter representing the action to be performed and `value` is
     * an integer argument for that action.
     *
     * Recognized instructions are as follows:
     *
     * - `N`: Move north by calling [move] with [Direction.UP] and a distance of `value`.
     * - `S`: Move south by calling [move] with [Direction.DOWN] and a distance of `value`.
     * - `E`: Move east by calling [move] with [Direction.RIGHT] and a distance of `value`.
     * - `W`: Move west by calling [move] with [Direction.LEFT] and a distance of `value`.
     * - `L`: Turn left by calling [turnLeft] with an angle of `value` degrees.
     * - `R`: Turn right by calling [turnRight] with an angle of `value` degrees.
     * - `F`: Go forward by calling [goForward] with a count of `value`.
     */
    fun followInstruction(instruction: String) {
        val (action, value) = parseInstruction(instruction)
        when (action) {
            'N' -> move(Direction.UP, value)
            'S' -> move(Direction.DOWN, value)
            'E' -> move(Direction.RIGHT, value)
            'W' -> move(Direction.LEFT, value)
            'L' -> turnLeft(RotationAngle.fromDegrees(value))
            'R' -> turnRight(RotationAngle.fromDegrees(value))
            'F' -> goForward(value)
            else -> throw IllegalArgumentException("Unknown action: $action")
        }
    }

    companion object {
        /**
         * A regex used to parse an instruction string.
         */
        private val INSTRUCTION_REGEX = Regex("""([A-Z])(\d+)""")

        /**
         * Returns the pair `(action, value)` from an [instruction] string of the form
         * `"${action}${value}"`, where `action` is a single uppercase letter and `value` is a
         * decimal integer.
         *
         * @throws IllegalArgumentException If [instruction] is not of the correct form.
         */
        private fun parseInstruction(instruction: String): Pair<Char, Int> {
            // Use a regex to match against the instruction string.
            val matchResult = INSTRUCTION_REGEX.matchEntire(instruction)
            require(matchResult != null) { "Malformed instruction: $instruction" }

            // Extract and parse matched group values.
            val (actionString, valueString) = matchResult.destructured
            return Pair(actionString.first(), valueString.toInt())
        }
    }
}
