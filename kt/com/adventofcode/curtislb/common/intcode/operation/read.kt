package com.adventofcode.curtislb.common.intcode.operation

import com.adventofcode.curtislb.common.intcode.Intcode
import com.adventofcode.curtislb.common.intcode.mode.Mode

/**
 * Processes a single read operation (opcode: [Operation.READ]).
 *
 * @param intcode The currently running Intcode program.
 * @param cursor The 0-indexed position of the current operation to be processed.
 * @return The position to which [cursor] should move following the operation.
 *
 * @throws IllegalArgumentException If there are no parameters, or if the parameter is invalid.
 * @throws IllegalStateException If the program's input sequence has been exhausted.
 */
fun processRead(intcode: Intcode, cursor: Int): Int {
    val dest = intcode.readParameter(cursor)
    Mode.checkIsPosition(intcode, cursor, 0)
    intcode[dest] = intcode.nextInput()
    return cursor + 2
}
