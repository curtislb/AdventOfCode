package com.curtislb.adventofcode.year2020.day08.bootcode.operation

import com.curtislb.adventofcode.year2020.day08.bootcode.State

/**
 * An operation recognized by a boot code program, which may modify the program's state.
 */
interface Operation {
    /**
     * Returns the newly modified program [state] after executing this operation with the given
     * [argument].
     */
    fun execute(argument: Int, state: State): State

    companion object {
        /**
         * Returns the operation corresponding to [string].
         *
         * @throws IllegalArgumentException If [string] has no corresponding operation.
         */
        fun from(string: String): Operation = when (string) {
            "acc" -> AccumulateOperation
            "jmp" -> JumpOperation
            "nop" -> NoOperation
            else -> throw IllegalArgumentException("Invalid operation: $string")
        }
    }
}
