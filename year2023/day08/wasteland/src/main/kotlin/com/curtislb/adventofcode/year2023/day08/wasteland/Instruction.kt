package com.curtislb.adventofcode.year2023.day08.wasteland

/**
 * An instruction for moving from one node to the next in a [WastelandNetwork].
 */
enum class Instruction {
    /**
     * Instruction to move left from the current node.
     */
    LEFT,

    /**
     * Instruction to move right from the current node.
     */
    RIGHT;

    companion object {
        /**
         * Returns the [Instruction] that corresponds to the given [char].
         *
         * @throws IllegalArgumentException If [char] has no corresponding instruction.
         */
        fun fromChar(char: Char): Instruction = when (char) {
            'L' -> LEFT
            'R' -> RIGHT
            else -> throw IllegalArgumentException("Invalid instruction character: $char")
        }
    }
}
