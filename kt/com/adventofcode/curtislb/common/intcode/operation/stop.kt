package com.adventofcode.curtislb.common.intcode.operation

/**
 * Processes a single stop operation (opcode: [Operation.STOP]).
 * @return The position to which the program cursor should move following the operation.
 */
fun processStop(): Int = -1 // Stop program by returning OOB cursor position.
