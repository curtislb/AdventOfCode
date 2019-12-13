package com.adventofcode.curtislb.common.intcode.operation

import com.adventofcode.curtislb.common.intcode.Intcode

/**
 * Constants and utilities related to operations recognized by an Intcode program. Operations may take parameters, read
 * program input, perform some action(s), and move the program cursor to a new location. Supported operations are:
 * - Opcode 01: Takes three parameters `(A, B, C)`. Adds `A` and `B` and stores the result at position `C`.
 * - Opcode 02: Takes three parameters `(A, B, C)`. Multiplies `A` with `B` and stores the result at position `C`.
 * - Opcode 03: Takes one parameter `(A)`. Reads a single value from the program input and stores it at position `A`.
 * - Opcode 04: Takes one parameter `(A)`. Writes `A` to standard output.
 * - Opcode 05: Takes two parameters `(A, B)`. If `A` is non-zero, sets the cursor to `B`.
 * - Opcode 06: Takes two parameters `(A, B)`. If `A` is zero, sets the cursor to `B`.
 * - Opcode 07: Takes three parameters `(A, B, C)`. Stores the value `if (A < B) 1 else 0` at position `C`.
 * - Opcode 08: Takes three parameters `(A, B, C)`. Stores the value `if (A == B) 1 else 0` at position `C`.
 * - Opcode 99: Takes no parameters. Halts execution of the program.
 */
object Operation {
    private const val ADD = 1
    private const val MULTIPLY = 2
    private const val READ = 3
    private const val WRITE = 4
    private const val JUMP_TRUE = 5
    private const val JUMP_FALSE = 6
    private const val LESS_THAN = 7
    private const val EQUALS = 8
    private const val STOP = 99

    /**
     * Processes a single opcode and returns a new position for the program cursor.
     *
     * @param intcode The currently running Intcode program.
     * @param cursor The 0-indexed position of the operation to be processed.
     * @return The position to which [cursor] should move following the operation.
     *
     * @throws IllegalArgumentException If an unknown opcode is encountered, or if the number or value of parameters
     *  provided to any operation is invalid.
     */
    fun process(intcode: Intcode, cursor: Int): Int {
        return when (val opcode = intcode[cursor] % 100) {
            ADD -> processAdd(intcode, cursor)
            MULTIPLY -> processMultiply(intcode, cursor)
            READ -> processRead(intcode, cursor)
            WRITE -> processWrite(intcode, cursor)
            JUMP_TRUE -> processJumpIfTrue(intcode, cursor)
            JUMP_FALSE -> processJumpIfFalse(intcode, cursor)
            LESS_THAN -> processLessThan(intcode, cursor)
            EQUALS -> processEquals(intcode, cursor)
            STOP -> processStop()
            else -> throw IllegalArgumentException("Unknown opcode $opcode at position $cursor")
        }
    }
}
