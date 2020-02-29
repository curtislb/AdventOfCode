package com.curtislb.adventofcode.common.intcode.operation

import com.curtislb.adventofcode.common.intcode.Intcode
import com.curtislb.adventofcode.common.intcode.mode.Mode
import java.math.BigInteger

/**
 * An operation recognized by an [Intcode] program, which may modify the program's state, return a new position for the
 * program cursor, and have side effects such as printing to standard output
 */
interface Operation {
    /**
     * The number of parameters needed by this [Operation].
     */
    val parameterCount: Int

    /**
     * Processes this operation for the [intcode] program, with the given [parameters] and parameter [modes], and
     * returns the position to which [cursor] should be updated following the operation.
     */
    fun process(intcode: Intcode, cursor: Int, parameters: Array<BigInteger>, modes: Array<Mode>): Int

    companion object {
        /**
         * Returns the operation corresponding to [opcode].
         *
         * @throws IllegalArgumentException If [opcode] has no corresponding operation.
         */
        fun from(opcode: Int): Operation = when (opcode) {
            1 -> AddOperation
            2 -> MultiplyOperation
            3 -> InputOperation
            4 -> OutputOperation
            5 -> JumpIfTrueOperation
            6 -> JumpIfFalseOperation
            7 -> LessThanOperation
            8 -> EqualsOperation
            9 -> MoveRelativeBaseOperation
            99 -> StopOperation
            else -> throw IllegalArgumentException("Unknown operation: $opcode")
        }

        /**
         * Returns the operation corresponding to [opcode].
         *
         * @throws IllegalArgumentException If [opcode] has no corresponding operation.
         */
        fun from(opcode: BigInteger): Operation = from(opcode.toInt())
    }
}
