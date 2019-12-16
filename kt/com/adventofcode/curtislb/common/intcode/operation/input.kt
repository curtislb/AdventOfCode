package com.adventofcode.curtislb.common.intcode.operation

import com.adventofcode.curtislb.common.intcode.Intcode
import com.adventofcode.curtislb.common.intcode.mode.Mode

/**
 * Processes a single input operation (opcode: [Operation.INPUT]).
 *
 * If no next input is available, this function immediately returns without providing a new position for [cursor].
 *
 * @param intcode The currently running Intcode program.
 * @param cursor The 0-indexed position of the current operation to be processed.
 * @return The position to which [cursor] should move following the operation.
 *
 * @throws IllegalArgumentException If there are no parameters, or if the parameter is invalid.
 */
fun processInput(intcode: Intcode, cursor: Int): Int {
    val input = intcode.nextInput() ?: return cursor
    val dest = intcode.getParameter(cursor)
    Mode.checkIsPosition(intcode, cursor, 0)
    intcode[dest] = input
    return cursor + 2
}
