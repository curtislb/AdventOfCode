package com.adventofcode.curtislb.common.intcode

import com.adventofcode.curtislb.common.intcode.operation.Operation

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
    private lateinit var inputStream: Iterator<Int>

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
     * The program begins with its cursor at position 0 and processes the operation located there. Each operation is
     * identified by a two-digit opcode and may read from [input], modify the program's state, move the cursor to a new
     * position, and have side effects such as printing to standard output (see [Operation]). The program will continue
     * processing operations at the position of the cursor until the cursor is moved outside the range of the program.
     * At that point the program will halt, and this function will return.
     *
     * The integer representing each operation may also contain additional digits (read from lowest to highest order)
     * following the opcode. These digits represent the mode in which each parameter should be interpreted (see
     * [com.adventofcode.curtislb.common.intcode.mode.Mode]).
     *
     * @param input A (possibly infinite) [Sequence] of input values to be given to the program.
     *
     * @throws IllegalArgumentException If an unknown opcode or parameter mode is encountered, or if the number or value
     *  of parameters provided to any operation is invalid.
     * @throws IllegalStateException If an operation attempts to read a value from an empty input stream.
     * @throws IndexOutOfBoundsException If an operation attempts to access a position outside the range of the program.
     */
    fun run(input: Sequence<Int> = emptySequence()) {
        inputStream = input.iterator()
        var cursor = 0
        while (cursor in currentValues.indices) {
            cursor = Operation.process(this, cursor)
        }
    }

    /**
     * Reads the next input value for the program.
     * @return The next value from the [Sequence] [inputStream].
     * @throws IllegalStateException If [inputStream] has no next value.
     */
    internal fun nextInput(): Int {
        if (!inputStream.hasNext()) {
            throw IllegalStateException("Can't read next value. Input stream exhausted.")
        }
        return inputStream.next()
    }

    /**
     * Gets the first parameter of the operation at a given position.
     * @param cursor The 0-indexed position of the operation in the program.
     * @return The value of the the operation's first parameter (before applying parameter modes).
     */
    internal fun readParameter(cursor: Int): Int {
        checkSufficientParameters(cursor, 1)
        return currentValues[cursor + 1]
    }

    /**
     * Gets the parameters of the operation at a given position.
     * @param cursor The 0-indexed position of the operation in the program.
     * @param paramCount The number of parameters required by the operation.
     * @return A [List] containing the values of the operation's parameters (before applying parameter modes).
     */
    internal fun readParameters(cursor: Int, paramCount: Int): List<Int> {
        checkSufficientParameters(cursor, paramCount)
        return currentValues.slice((cursor + 1)..(cursor + paramCount))
    }

    /**
     * Checks that there is a sufficient number of parameters following an operation.
     *
     * @param cursor The position in the program that contains the current operation.
     * @param paramCount The number of parameters required for the current operation.
     *
     * @throws IllegalArgumentException If fewer than [paramCount] parameters follow [cursor] in the program.
     */
    private fun checkSufficientParameters(cursor: Int, paramCount: Int) {
        if (cursor + paramCount >= currentValues.size) {
            val operation = currentValues[cursor]
            throw IllegalArgumentException("Too few parameters for operation $operation at position $cursor")
        }
    }
}
