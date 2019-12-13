package com.adventofcode.curtislb.common.intcode.operation

import com.adventofcode.curtislb.common.intcode.Intcode
import com.adventofcode.curtislb.common.intcode.mode.Mode

/**
 * Processes a single write operation (opcode: [Operation.WRITE]).
 *
 * @param intcode The currently running Intcode program.
 * @param cursor The 0-indexed position of the current operation to be processed.
 * @return The position to which [cursor] should move following the operation.
 *
 * @throws IllegalArgumentException If there are no parameters, or if the parameter is invalid.
 */
fun processWrite(intcode: Intcode, cursor: Int): Int {
    val param = intcode.readParameter(cursor)
    val value = Mode.apply(intcode, cursor, param)
    println(value)
    return cursor + 2
}
