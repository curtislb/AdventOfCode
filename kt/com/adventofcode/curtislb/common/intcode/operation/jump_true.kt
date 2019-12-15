package com.adventofcode.curtislb.common.intcode.operation

import com.adventofcode.curtislb.common.intcode.Intcode
import com.adventofcode.curtislb.common.intcode.mode.Mode

/**
 * Processes a single jump-if-true operation (opcode: [Operation.JUMP_TRUE]).
 *
 * @param intcode The currently running Intcode program.
 * @param cursor The 0-indexed position of the current operation to be processed.
 * @return The position to which [cursor] should move following the operation.
 *
 * @throws IllegalArgumentException If there are fewer than two parameters, or if any parameter is invalid.
 */
fun processJumpIfTrue(intcode: Intcode, cursor: Int): Int {
    val (param1, param2) = intcode.getParameters(cursor, 2)
    val (condition, position) = Mode.applyAll(intcode, cursor, param1, param2)
    return if (condition != 0) position else cursor + 3
}
