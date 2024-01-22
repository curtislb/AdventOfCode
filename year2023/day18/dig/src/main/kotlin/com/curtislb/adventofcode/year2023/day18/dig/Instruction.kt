package com.curtislb.adventofcode.year2023.day18.dig

import com.curtislb.adventofcode.common.geometry.Direction

/**
 * A single instruction that may appear in a [DigPlan].
 *
 * @property direction The direction to move, relative to the current position.
 * @property distance The number of meters to move while digging out one-meter cubes.
 */
data class Instruction(val direction: Direction, val distance: Int) {
    init {
        require(!direction.isDiagonal()) { "Direction must be cardinal: $direction" }
    }

    companion object {
        /**
         * A regex used to parse instruction parameters from a single line of a dig plan.
         */
        private val INSTRUCTION_REGEX = Regex("""([UDLR]) (\d+) \(#([0-9a-f]{5})([0-3])\)""")

        /**
         * Returns an [Instruction] with a [direction] and [distance] read from the given [string].
         *
         * The [string] must have the following format, where `directionChar` is a single character
         * (`U`, `D`, `L`, or `R`) corresponding to a cardinal [Direction], `directionInt` is a
         * decimal integer, `distanceHex` is a 5-digit hexadecimal number, and `directionHex` is a
         * digit corresponding to a cardinal [Direction] (0 to [Direction.RIGHT], 1 to
         * [Direction.DOWN], 2 to [Direction.LEFT], or 3 to [Direction.UP]):
         *
         * ```
         * $directionChar $distanceInt (#${distanceHex}${directionHex})
         * ```
         *
         * If [useHexCode] is `false`, the direction for the instruction will be parsed from
         * `directionChar` and the [distance] will be parsed from `distanceInt`. If [useHexCode] is
         * `true`, the [direction] will be parsed from `directionHex` and the [distance] will be
         * parsed from `distanceHex`.
         *
         * @throws IllegalArgumentException If [string] is formatted incorrectly.
         */
        fun fromString(string: String, useHexCode: Boolean = false): Instruction {
            val matchResult = INSTRUCTION_REGEX.matchEntire(string)
            require(matchResult != null) { "Malformed instruction string: $string" }

            val direction: Direction
            val distance: Int
            if (useHexCode) {
                // Read direction and distance from the third (hex code) token
                val (_, _, distanceHex, directionHex) = matchResult.destructured
                direction = when (directionHex) {
                    "0" -> Direction.RIGHT
                    "1" -> Direction.DOWN
                    "2" -> Direction.LEFT
                    "3" -> Direction.UP
                    else -> error("Invalid direction hex string: $directionHex")
                }
                distance = distanceHex.toInt(radix = 16)
            } else {
                // Read direction and distance from first two tokens
                val (directionString, distanceString, _, _) = matchResult.destructured
                direction = Direction.fromChar(directionString[0])
                distance = distanceString.toInt()
            }

            return Instruction(direction, distance)
        }
    }
}
