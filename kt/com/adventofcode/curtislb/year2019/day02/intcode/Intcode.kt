package com.adventofcode.curtislb.year2019.day02.intcode

/**
 * An Intcode program, consisting of a sequence of integer values that may be modified during execution.
 *
 * @param programString A string representation of the Intcode program, with comma-separated values.
 *
 * @throws IllegalArgumentException If given an empty program string.
 * @throws NumberFormatException If any value doesn't represent a valid integer.
 */
class Intcode(programString: String) {
    private val initialValues: List<Int>
    private var currentValues: MutableList<Int>

    init {
        val tokens = programString.split(',')
        if (tokens.isEmpty()) {
            throw IllegalArgumentException("Program must not be empty")
        }
        initialValues = tokens.map { it.toInt() }
        currentValues = initialValues.toMutableList()
    }

    /**
     * The number of integer values contained in the program.
     */
    val size: Int get() = currentValues.size

    /**
     * Gets the current value of an integer at a given position in the program.
     * @param i The 0-indexed position of the desired program value.
     * @return The current integer value stored at position [i].
     */
    operator fun get(i: Int): Int = currentValues[i]

    /**
     * Sets the value of an integer at a given position in the program.
     * @param i The 0-indexed position of the program value to be set.
     * @param value The new integer value to be stored at position [i].
     */
    operator fun set(i: Int, value: Int) { currentValues[i] = value }

    /**
     * Restores the program to its starting state by setting all integers to their original post-initialization values.
     */
    fun reset() { currentValues = initialValues.toMutableList() }

    /**
     * Runs the program by processing opcodes one at a time until a halting condition is reached.
     *
     * Opcodes and their arguments are processed from left to right as follows:
     * - Opcode 1: Takes three arguments (A, B, C). Adds the value at position A to the value at position B and stores
     *             the resulting sum at position C.
     * - Opcode 2: Takes three arguments (A, B, C). Multiplies the value at position A with the value at B and stores
     *             the resultint product at position C.
     * - Opcode 99: Takes no arguments. Halts execution of the program.
     *
     * The program will also halt if the cursor advances past the end of the program (all opcodes have been processed).
     *
     * @throws IllegalStateException If an unknown opcode is encountered, or if the number or value of parameters
     * provided to any opcode is invalid.
     */
    fun run() {
        var cursor = 0
        while (cursor in currentValues.indices) {
            cursor = processOpcode(cursor)
        }
    }

    /**
     * Processes a single opcode and returns a new position for the program cursor.
     *
     * @param cursor The 0-indexed position of the current opcode to be processed.
     * @return The new position to which [cursor] should advance.
     *
     * @throws IllegalStateException If an unknown opcode is encountered, or if the number or value of parameters
     * provided to any opcode is invalid.
     */
    private fun processOpcode(cursor: Int): Int {
        return when (val opcode = currentValues[cursor]) {
            OP_ADD -> processAdd(cursor)
            OP_MULTIPLY -> processMultiply(cursor)
            OP_STOP -> -1 // Stop program by returning OOB cursor position.
            else -> throw IllegalStateException("Unknown opcode $opcode at position $cursor")
        }
    }

    /**
     * Processes a single add operation (opcode 1).
     *
     * @param cursor The 0-indexed position of the current opcode to be processed.
     * @return The new position to which [cursor] should advance.
     *
     * @throws IllegalStateException If there are fewer than three parameters, or if any position argument is invalid.
     */
    private fun processAdd(cursor: Int): Int = processBinaryOp(cursor) { a, b -> a + b }

    /**
     * Processes a single multiply operation (opcode 2).
     *
     * @param cursor The 0-indexed position of the current opcode to be processed.
     * @return The new position to which [cursor] should advance.
     *
     * @throws IllegalStateException If there are fewer than three parameters, or if any position argument is invalid.
     */
    private fun processMultiply(cursor: Int): Int = processBinaryOp(cursor) { a, b -> a * b }

    /**
     * Processes a single operation which combines two operands and stores the result.
     *
     * @param cursor The 0-indexed position of the current opcode to be processed.
     * @param binaryOp The operation that will be used to combine the two operands
     * @return The new position to which [cursor] should advance.
     *
     * @throws IllegalStateException If there are fewer than three parameters, or if any position argument is invalid.
     */
    private fun processBinaryOp(cursor: Int, binaryOp: (Int, Int) -> Int): Int {
        val opcode = currentValues[cursor]
        if (cursor + 3 >= currentValues.size) {
            throw IllegalStateException("Too few arguments for opcode $opcode at position $cursor")
        }

        val (operand1, operand2, result) = currentValues.slice(cursor + 1 .. cursor + 3)
        val validIndices = currentValues.indices
        if (operand1 !in validIndices || operand2 !in validIndices || result !in validIndices) {
            throw IllegalStateException("Invalid position parameter(s) for opcode $opcode at position $cursor")
        }

        currentValues[result] = binaryOp(currentValues[operand1], currentValues[operand2])
        return cursor + 4
    }

    companion object {
        private const val OP_ADD = 1
        private const val OP_MULTIPLY = 2
        private const val OP_STOP = 99
    }
}
