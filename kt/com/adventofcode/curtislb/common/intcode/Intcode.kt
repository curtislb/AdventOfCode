package com.adventofcode.curtislb.common.intcode

/**
 * An Intcode program, consisting of a sequence of integer values that may be modified during execution.
 *
 * @param programString A string representation of the Intcode program, with comma-separated values.
 * @param input An [Iterable] over all input values to be given in order to the program.
 *
 * @throws IllegalArgumentException If given an empty program string.
 * @throws NumberFormatException If any value doesn't represent a valid integer.
 */
class Intcode(programString: String, input: Iterable<Int> = emptyList()) {
    private val initialValues: List<Int>
    private var currentValues: MutableList<Int>
    private val inputStream: Iterator<Int>

    init {
        val tokens = programString.split(',')
        if (tokens.isEmpty()) {
            throw IllegalArgumentException("Program must not be empty")
        }
        initialValues = tokens.map { it.toInt() }
        currentValues = initialValues.toMutableList()
        inputStream = input.iterator()
    }

    /**
     * The number of integer values contained in the program.
     */
    val size: Int
        get() = currentValues.size

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
     * Runs the program by processing operations one at a time until a halting condition is reached.
     *
     * Operations and their arguments are processed from left to right as follows:
     * - Opcode 01: Takes three parameters `(A, B, C)`. Adds `A` and `B` and stores the result at position `C`.
     * - Opcode 02: Takes three parameters `(A, B, C)`. Multiplies `A` with `B` and stores the result at position `C`.
     * - Opcode 03: Takes one parameter `(A)`. Reads a single value from [inputStream] and stores it at position `A`.
     * - Opcode 04: Takes one parameter `(A)`. Writes `A` to standard output.
     * - Opcode 99: Takes no parameters. Halts execution of the program.
     *
     * In addition, the integer representing each operation may also contain additional 0 or 1 digits (read from lowest
     * to highest order digit, following the two-digit opcode). These digits represent the mode in which each
     * parameter's value should be interpreted and are as follows:
     * - Mode 0 (default): Position mode. This parameter's value refers to a 0-indexed position in the Intcode program.
     * - Mode 1: Immediate mode. This parameter's value should be used directly as an argument.
     *
     * Note that the program will also halt if the cursor advances or is otherwise moved outside of the range of
     * integer value positions that make up the program.
     *
     * @throws IllegalStateException If an unknown opcode is encountered, or if the number or value of parameters
     *  provided to any opcode is invalid.
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
     *  provided to any opcode is invalid.
     */
    private fun processOpcode(cursor: Int): Int {
        return when (val opcode = currentValues[cursor] % 100) {
            OP_ADD -> processAdd(cursor)
            OP_MULTIPLY -> processMultiply(cursor)
            OP_READ -> processRead(cursor)
            OP_WRITE -> processWrite(cursor)
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
        checkSufficientParameters(3, cursor, opcode)

        val (operand1, operand2, result) = currentValues.slice((cursor + 1)..(cursor + 3))
        val validIndices = currentValues.indices
        if (operand1 !in validIndices || operand2 !in validIndices || result !in validIndices) {
            throw IllegalStateException("Invalid position parameter(s) for opcode $opcode at position $cursor")
        }

        currentValues[result] = binaryOp(currentValues[operand1], currentValues[operand2])
        return cursor + 4
    }

    /**
     * TODO
     */
    private fun processRead(cursor: Int): Int {
        checkSufficientParameters(1, cursor, OP_READ)
        if (!inputStream.hasNext()) {
            throw IllegalStateException("Can't read next value. Input stream exhausted.")
        }

        val dest = currentValues[cursor + 1]
        if (dest !in currentValues.indices) {
            throw IllegalStateException("Invalid position parameter for opcode $OP_READ at position $cursor")
        }

        currentValues[dest] = inputStream.next()
        return cursor + 2
    }

    /**
     * TODO
     */
    private fun processWrite(cursor: Int): Int {
        checkSufficientParameters(1, cursor, OP_WRITE)

        val source = currentValues[cursor + 1]
        if (source !in currentValues.indices) {
            throw IllegalStateException("Invalid position parameter for opcode $OP_READ at position $cursor")
        }

        println(currentValues[source])
        return cursor + 2
    }

    /**
     * TODO
     */
    private fun checkSufficientParameters(paramCount: Int, cursor: Int, opcode: Int) {
        if (cursor + paramCount >= currentValues.size) {
            throw IllegalStateException("Fewer than $paramCount parameters for opcode $opcode at position $cursor")
        }
    }

    companion object {
        private const val OP_ADD = 1
        private const val OP_MULTIPLY = 2
        private const val OP_READ = 3
        private const val OP_WRITE = 4
        private const val OP_STOP = 99
    }
}
