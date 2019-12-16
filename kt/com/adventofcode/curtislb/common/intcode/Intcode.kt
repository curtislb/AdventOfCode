package com.adventofcode.curtislb.common.intcode

import com.adventofcode.curtislb.common.collection.ValueSequencer
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
    // Program values
    private val initialValues: List<Int>
    private var currentValues: MutableList<Int>

    // Program I/O
    private val input: ValueSequencer<Int> = ValueSequencer()
    var onOutput: (Int) -> Unit = { println(it) }

    // Additional program state
    private var cursorStart: Int = 0
    private var isPaused: Boolean = false

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
     * Whether this program has halted and no more operations can be run.
     */
    val isDone: Boolean
        get() = cursorStart !in currentValues.indices

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
     * Runs the program by processing operations one at a time until a halting condition is reached.
     *
     * The program begins processing operations at its last cursor position (0 for a new program). Each operation is
     * identified by a two-digit opcode and may read from [input], modify the program's state, move the cursor to a new
     * position, and have side effects such as printing to standard output (see [Operation]).
     *
     * The integer representing each operation may also contain additional digits (read from lowest to highest order)
     * following the opcode. These digits represent the mode in which each parameter should be interpreted (see
     * [com.adventofcode.curtislb.common.intcode.mode.Mode]).
     *
     * The program will continue processing operations at the position of the cursor until one of the following occurs:
     * - The cursor is moved outside the range of the program, at which point the program will halt.
     * - The program requests input, but no next input has been provided, at which point the program will pause.
     *
     * In either case, this function will return, and the cursor position from which execution will resume is stored.
     * Once the program has halted (as opposed to paused), any future call to this function will immediately return
     * until [reset] is invoked.
     *
     * @throws IllegalArgumentException If an unknown opcode or parameter mode is encountered, or if the number or value
     *  of parameters provided to any operation is invalid.
     * @throws IndexOutOfBoundsException If an operation attempts to access a position outside the range of the program.
     */
    fun run() {
        var cursor = cursorStart
        isPaused = false
        while (cursor in currentValues.indices && !isPaused) {
            cursor = Operation.process(this, cursor)
        }
        cursorStart = cursor
    }

    /**
     * Restores various aspects of the program to their starting states. These include:
     * - Any program values modified during execution
     * - Any input provided to the program
     * - The last cursor position of the program
     * - Whether the program has been paused
     *
     * In particular, note that the value of [onOutput] is *not* reset by this method.
     */
    fun reset() {
        currentValues = initialValues.toMutableList()
        input.clear()
        cursorStart = 0
        isPaused = false
    }

    /**
     * Queues new input values for the program.
     * @param inputValues New or additional values to be read (in order) as input.
     */
    fun sendInput(vararg inputValues: Int) { sendInput(inputValues.asSequence()) }

    /**
     * Queues new input values for the program.
     * @param inputSequence A (possibly infinite) [Sequence] of new or additional input values to be read.
     */
    fun sendInput(inputSequence: Sequence<Int>) { input.queue(inputSequence) }

    /**
     * Reads the next input value for the program.
     *
     * If no next input is available, this method instead returns `null` and indicates that the program should pause to
     * wait for additional input.
     *
     * @return The next value read from [input], or `null` if none is available.
     */
    internal fun nextInput(): Int? {
        if (!input.hasNext()) {
            isPaused = true
            return null
        }
        return input.next()
    }

    /**
     * Gets the first parameter of the operation at a given position.
     *
     * @param cursor The 0-indexed position of the operation in the program.
     * @return The value of the the operation's first parameter (before applying parameter modes).
     *
     * @throws IllegalArgumentException If no parameters follow [cursor] in the program.
     */
    internal fun getParameter(cursor: Int): Int {
        checkSufficientParameters(cursor, 1)
        return currentValues[cursor + 1]
    }

    /**
     * Gets the parameters of the operation at a given position.
     *
     * @param cursor The 0-indexed position of the operation in the program.
     * @param paramCount The number of parameters required by the operation.
     * @return A [List] containing the values of the operation's parameters (before applying parameter modes).
     *
     * @throws IllegalArgumentException If fewer than [paramCount] parameters follow [cursor] in the program.
     */
    internal fun getParameters(cursor: Int, paramCount: Int): List<Int> {
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
