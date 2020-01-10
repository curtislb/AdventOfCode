package com.adventofcode.curtislb.common.intcode

import com.adventofcode.curtislb.common.collection.ValueSequencer
import com.adventofcode.curtislb.common.intcode.mode.Mode
import com.adventofcode.curtislb.common.intcode.mode.PositionMode
import com.adventofcode.curtislb.common.intcode.mode.toMode
import com.adventofcode.curtislb.common.intcode.operation.Operation
import com.adventofcode.curtislb.common.intcode.operation.toOperation
import java.io.File
import java.math.BigInteger

/**
 * An Intcode program, consisting of arbitrary-length integer values that may be modified during execution.
 * @param programString A string representation of the program, with comma-separated values.
 * @throws IllegalArgumentException If given an empty program string.
 */
class Intcode(programString: String) {
    // Program values
    private val initialValues: List<BigInteger>
    private var currentValues: Array<BigInteger>
    private var extendedValues: MutableMap<Int, BigInteger> = mutableMapOf()

    // Program I/O
    private val input: ValueSequencer<BigInteger> = ValueSequencer()
    var onOutput: (output: BigInteger) -> Unit = { println(it) }

    // Additional program state
    internal var relativeBase: Int = 0
    private var cursorStart: Int = 0

    init {
        val tokens = programString.split(',')
        if (tokens.isEmpty()) {
            throw IllegalArgumentException("Program must not be empty.")
        }
        initialValues = tokens.map { it.toBigInteger() }
        currentValues = initialValues.toTypedArray()
    }

    /**
     * An Intcode program, consisting of arbitrary-length integer values that may be modified during execution.
     * @param file A [File] containing a representation of the program with comma-separated values.
     * @throws IllegalArgumentException If given an empty file.
     */
    constructor(file: File) : this(file.readText().trim())

    /**
     * Whether this program has halted and no more operations can be run.
     */
    val isDone: Boolean
        get() = cursorStart !in currentValues.indices

    /**
     * Whether this program has paused and may be resumed by calling [run].
     */
    var isPaused: Boolean = false
        private set

    /**
     * Gets the current value at a given position in the program.
     * @param index The 0-indexed position of the desired value.
     * @return The current value stored at position [index].
     */
    operator fun get(index: Int): BigInteger {
        return when {
            index in currentValues.indices -> currentValues[index]
            index > currentValues.lastIndex -> extendedValues.getOrDefault(index, DEFAULT_VALUE)
            else -> throw IndexOutOfBoundsException("Can't access negative position: $index")
        }
    }

    /**
     * Sets the value at a given position in the program.
     * @param index The 0-indexed position of the value to be set.
     * @param value The new value to be stored at position [index].
     */
    operator fun set(index: Int, value: BigInteger) {
        when {
            index in currentValues.indices -> currentValues[index] = value
            index > currentValues.lastIndex -> {
                if (index in extendedValues && value == DEFAULT_VALUE) {
                    extendedValues.remove(index)
                } else if (index in extendedValues || value != DEFAULT_VALUE) {
                    extendedValues[index] = value
                }
            }
            else -> throw IndexOutOfBoundsException("Can't access negative position: $index")
        }
    }

    /**
     * Queues new input values for the program.
     * @param inputValues Additional values to be read (in order) as input.
     */
    fun sendInput(vararg inputValues: BigInteger) { sendInput(inputValues.asSequence()) }

    /**
     * Queues new input values for the program.
     * @param inputSequence A (possibly infinite) [Sequence] of additional values to be read as input.
     */
    fun sendInput(inputSequence: Sequence<BigInteger>) { input.queue(inputSequence) }

    /**
     * Reads the next input value for the program.
     *
     * If no next input is available, this method instead returns `null` and indicates that the program should pause to
     * wait for additional input.
     *
     * @return The next value read from [input], or `null` if none is available.
     */
    internal fun nextInput(): BigInteger? {
        if (!input.hasNext()) {
            isPaused = true
            return null
        }
        return input.next()
    }

    /**
     * Runs the program by processing operations one at a time until a halting condition is reached.
     *
     * The program begins processing operations at its last cursor position (0 for a new program). Each operation
     * contains a two-digit opcode (see [Operation]) and may contain additional digits representing the mode(s) in which
     * parameters for that operation should be interpreted (see [Mode]). After processing an operation, the program will
     * move its cursor to a new position and repeat the process. This will continue until one of the following occurs:
     * - The cursor is moved to an invalid (negative) position, at which point the program will halt.
     * - The program requests input, but no next input is available, at which point the program will pause.
     *
     * In either case, this function will return, and the program's last cursor position will be stored. Note that once
     * the program has halted (as opposed to paused), any future calls to [run] will immediately return, until [reset]
     * is invoked.
     */
    fun run() {
        var cursor = cursorStart
        isPaused = false
        while (cursor >= 0 && !isPaused) {
            val (operation, modes) = parseOperation(cursor)
            val parameters = getParameters(cursor, operation.parameterCount)
            cursor = operation.process(this, cursor, parameters, modes)
        }
        cursorStart = cursor
    }

    /**
     * Parses the value of an operation at a given position in the program.
     * @param cursor The position in the program containing the operation value to be parsed.
     * @return A [Pair] containing the [Operation] to be performed and an [Array] of modes to be applied to its
     *  parameters.
     */
    private fun parseOperation(cursor: Int): Pair<Operation, Array<Mode>> {
        var (modesInt, opcodeInt) = this[cursor].divideAndRemainder(OPCODE_MOD)
        val operation = opcodeInt.toOperation()

        val modes = Array<Mode>(operation.parameterCount) { PositionMode }
        for (i in 0 until operation.parameterCount) {
            if (modesInt == BigInteger.ZERO) {
                break
            }
            val (newModesInt, modeInt) = modesInt.divideAndRemainder(BigInteger.TEN)
            modesInt = newModesInt
            modes[i] = modeInt.toMode()
        }

        return Pair(operation, modes)
    }

    /**
     * Gets the parameters for an operation at a given position.
     * @param cursor The position of the operation in the program.
     * @param parameterCount The number of parameters required by the operation.
     * @return An [Array] containing the parameters for the operation (before applying parameter modes).
     */
    private fun getParameters(cursor: Int, parameterCount: Int): Array<BigInteger> {
        return Array(parameterCount) { this[cursor + it + 1] }
    }

    /**
     * Restores various aspects of the program to their starting states. These include:
     * - Any values modified during execution
     * - Any input provided to the program
     * - The relative base position of the program
     * - The last cursor position of the program
     * - Whether the program has been paused
     *
     * In particular, note that [onOutput] is **not** reset by this method.
     */
    fun reset() {
        currentValues = initialValues.toTypedArray()
        extendedValues = mutableMapOf()
        input.clear()
        relativeBase = 0
        cursorStart = 0
        isPaused = false
    }

    private companion object {
        /**
         * The default value to be assumed for any position in the program that isn't explicitly set.
         */
        val DEFAULT_VALUE: BigInteger = BigInteger.ZERO

        /**
         * The divisor used to separate the opcode and parameter modes of an operation value.
         */
        val OPCODE_MOD: BigInteger = BigInteger("100")
    }
}
