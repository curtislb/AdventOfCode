package com.curtislb.adventofcode.year2020.day08.bootcode

import com.curtislb.adventofcode.year2020.day08.bootcode.operation.Operation

/**
 * A single boot code instruction, consisting of an [operation] and an integer [argument].
 */
class Instruction(val operation: Operation, val argument: Int) {
    /**
     * Returns the new program [state] after executing [operation] with the given [argument].
     */
    fun execute(state: State): State = operation.execute(argument, state)

    companion object {
        /**
         * Returns the instruction corresponding to a [string] of the form `"$operation $argument"`.
         *
         * @throws IllegalArgumentException If [string] does not represent a valid instruction.
         */
        fun from(string: String): Instruction {
            val tokens = string.trim().split(' ')
            require(tokens.size == 2) { "Invalid instruction string: $string" }
            val (opString, argString) = tokens
            return Instruction(Operation.from(opString), argString.toInt())
        }
    }
}
