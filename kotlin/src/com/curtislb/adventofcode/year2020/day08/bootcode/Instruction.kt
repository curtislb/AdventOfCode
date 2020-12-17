package com.curtislb.adventofcode.year2020.day08.bootcode

import com.curtislb.adventofcode.year2020.day08.bootcode.operation.Operation

/**
 * TODO
 */
class Instruction(var operation: Operation, var argument: Int) {
    /**
     * TODO
     */
    fun execute(state: BootCode.State): BootCode.State = operation.execute(argument, state)

    companion object {
        /**
         * TODO
         */
        fun from(string: String): Instruction {
            val tokens = string.trim().split(' ')
            require(tokens.size == 2) { "Invalid instruction string: $string" }
            val (opString, argString) = tokens
            return Instruction(Operation.from(opString), argString.toInt())
        }
    }
}
