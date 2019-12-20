package com.adventofcode.curtislb.common.intcode.operation

import com.adventofcode.curtislb.common.intcode.Intcode
import com.adventofcode.curtislb.common.intcode.mode.Mode
import java.math.BigInteger

/**
 * An operation recognized by an [Intcode] program.
 *
 * This operation may read from [Intcode.input], modify the program's state, return a new position for the program
 * cursor, and have side effects such as printing to standard output
 */
interface Operation {
    /**
     * The number of parameters needed by this [Operation].
     */
    val parameterCount: Int

    /**
     * Processes this [Operation] for the given parameters and gives the next position for the program cursor.
     * @param intcode The currently running [Intcode] program.
     * @param cursor The current position of the program cursor.
     * @param parameters An [Array] of parameters to be read by this [Operation].
     * @param modes An [Array] of parameter modes indicating how each parameter should be interpreted.
     * @return An [Int] representing the position to which the cursor should move following this operation.
     */
    fun process(intcode: Intcode, cursor: Int, parameters: Array<BigInteger>, modes: Array<Mode>): Int
}

/**
 * Converts an integer opcode recognized by [Intcode] to its corresponding [Operation].
 * @receiver An [Int] representing a valid opcode.
 * @return The [Operation] object corresponding to this [Int].
 * @throws IllegalArgumentException If called for an [Int] with no corresponding [Operation].
 */
fun Int.toOperation(): Operation {
    return when (this) {
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
        else -> throw IllegalArgumentException("Unknown operation: $this")
    }
}
