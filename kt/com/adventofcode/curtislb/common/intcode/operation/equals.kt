package com.adventofcode.curtislb.common.intcode.operation

import com.adventofcode.curtislb.common.intcode.Intcode
import com.adventofcode.curtislb.common.intcode.mode.Mode

/**
 * Processes a single equals operation (opcode: [Operation.EQUALS]).
 *
 * @param intcode The currently running Intcode program.
 * @param cursor The 0-indexed position of the current operation to be processed.
 * @return The position to which [cursor] should move following the operation.
 *
 * @throws IllegalArgumentException If there are fewer than three parameters, or if any parameter is invalid.
 */
fun processEquals(intcode: Intcode, cursor: Int): Int {
    val (param1, param2, dest) = intcode.readParameters(cursor, 3)
    val (operand1, operand2) = Mode.applyAll(intcode, cursor, param1, param2)
    Mode.checkIsPosition(intcode, cursor, 2)
    intcode[dest] = if (operand1 == operand2) 1 else 0
    return cursor + 4
}
