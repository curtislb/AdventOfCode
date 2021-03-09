package com.curtislb.adventofcode.common.intcode

import com.curtislb.adventofcode.common.collection.ValueSequencer
import com.curtislb.adventofcode.common.intcode.mode.Mode
import com.curtislb.adventofcode.common.intcode.mode.PositionMode
import com.curtislb.adventofcode.common.intcode.operation.Operation
import java.io.File
import java.math.BigInteger

/**
 * An Intcode program, consisting of arbitrary-length integer values that may be modified during execution.
 *
 * @param programString A string representation of the program, consisting of comma-separated integer values.
 * @param onOutput A function to be run whenever a new output value is produced by this program.
 *
 * @throws IllegalArgumentException If `programString` is empty.
 */
class Intcode(programString: String, var onOutput: (output: BigInteger) -> Unit = DEFAULT_OUTPUT) {
    /**
     * The initial integer values for this program.
     */
    private val initialValues: List<BigInteger>

    init {
        val tokens = programString.split(',')
        require(tokens.isNotEmpty() && (tokens.size > 1 || tokens[0].isNotEmpty())) { "Program must not be empty." }
        initialValues = tokens.map { it.toBigInteger() }
    }

    /**
     * The current integer values for this program, which may have been modified during execution.
     */
    private var currentValues: Array<BigInteger> = initialValues.toTypedArray()

    /**
     * A map of integer values stored by this program at positions outside the range of [currentValues].
     */
    private var extendedValues: MutableMap<Int, BigInteger> = mutableMapOf()

    /**
     * A queued sequence of input values that have been given to the program.
     */
    private val input: ValueSequencer<BigInteger> = ValueSequencer()

    /**
     * The index from which the positions of relative parameter values are determined.
     */
    internal var relativeBase: Int = 0

    /**
     * The position where the instruction pointer should begin the next time the program is run.
     */
    private var pointerStart: Int = 0

    /**
     * Whether this program has finished and no more operations can be run.
     */
    val isDone: Boolean get() = pointerStart !in currentValues.indices

    /**
     * Whether this program has paused and may be resumed by calling [run].
     */
    var isPaused: Boolean = false
        private set

    /**
     * An Intcode program, consisting of arbitrary-length integer values that may be modified during execution.
     *
     * @param file A file containing comma-separated integer values representing the program.
     * @param onOutput A function to be run whenever a new output value is produced by this program.
     *
     * @throws IllegalArgumentException If [file] is empty.
     */
    constructor(file: File, onOutput: (output: BigInteger) -> Unit = { println(it) })
        : this(file.readText().trim(), onOutput)

    /**
     * Returns the current value stored at [position] in the program.
     */
    operator fun get(position: Int): BigInteger = when {
        position in currentValues.indices -> currentValues[position]
        position > currentValues.lastIndex -> extendedValues.getOrDefault(position, DEFAULT_VALUE)
        else -> throw IndexOutOfBoundsException("Can't access negative position: $position")
    }

    /**
     * Updates the [value] stored at [position] in the program.
     */
    operator fun set(position: Int, value: BigInteger) {
        when {
            position in currentValues.indices -> currentValues[position] = value
            position > currentValues.lastIndex -> {
                if (position in extendedValues && value == DEFAULT_VALUE) {
                    extendedValues.remove(position)
                } else if (position in extendedValues || value != DEFAULT_VALUE) {
                    extendedValues[position] = value
                }
            }
            else -> throw IndexOutOfBoundsException("Can't access negative position: $position")
        }
    }

    /**
     * Queues all values in [inputValues] to be sent as input to the program.
     */
    fun sendInput(vararg inputValues: BigInteger) {
        sendInput(inputValues.asSequence())
    }

    /**
     * Queues all values in the (possibly infinite) [inputSequence] to be sent as input to the program.
     */
    fun sendInput(inputSequence: Sequence<BigInteger>) {
        input.queue(inputSequence)
    }

    /**
     * Reads the next queued input value for the program, or returns `null` and pauses the program if no next input is
     * available.
     */
    internal fun nextInput(): BigInteger? {
        if (!input.hasNext()) {
            isPaused = true
            return null
        }
        return input.next()
    }

    /**
     * Runs the program by processing operations one at a time until a stopping condition is reached.
     *
     * The program begins processing operations at its last pointer position (0 for a new program). Each operation
     * contains a two-digit opcode (see [Operation]) and may contain additional digits representing the mode(s) in which
     * parameters for that operation should be interpreted (see [Mode]). After processing an operation, the program will
     * move its pointer to a new position and repeat the process. This will continue until one of the following occurs:
     * - The pointer is moved to an invalid (negative) position, at which point the program will *finish*.
     * - The program requests input, but no next input is available, at which point the program will *pause*.
     *
     * In either case, this method will return, and the program's last pointer position will be saved. Once the program
     * has *finished* (not *paused*), any future calls to [run] will immediately return, until [resetState] is invoked.
     */
    fun run() {
        var pointer = pointerStart
        isPaused = false
        while (pointer >= 0 && !isPaused) {
            val (operation, modes) = parseOperation(pointer)
            val parameters = getParameters(pointer, operation.parameterCount)
            pointer = operation.process(this, pointer, parameters, modes)
        }
        pointerStart = pointer
    }

    /**
     * Returns the operation and associated parameter modes at a given [pointer] location in the program.
     */
    private fun parseOperation(pointer: Int): Pair<Operation, Array<Mode>> {
        var (modesInt, opcodeInt) = this[pointer].divideAndRemainder(OPCODE_MOD)
        val operation = Operation.from(opcodeInt)

        val modes = Array<Mode>(operation.parameterCount) { PositionMode }
        for (i in 0 until operation.parameterCount) {
            if (modesInt == BigInteger.ZERO) {
                break
            }
            val (newModesInt, modeInt) = modesInt.divideAndRemainder(BigInteger.TEN)
            modesInt = newModesInt
            modes[i] = Mode.from(modeInt)
        }

        return Pair(operation, modes)
    }

    /**
     * Returns the first [parameterCount] parameters for an operation at a given [pointer] location in the program.
     */
    private fun getParameters(pointer: Int, parameterCount: Int): Array<BigInteger> {
        return Array(parameterCount) { this[pointer + it + 1] }
    }

    /**
     * Restores various aspects of the program to their starting states. These include:
     * - Any values modified during execution.
     * - Any input values provided to the program.
     * - The relative base position of the program.
     * - The last pointer position of the program.
     * - Whether the program has been paused.
     *
     * Notably, the [onOutput] function is *not* reset by this method. For that, use [resetOutput].
     */
    fun resetState() {
        currentValues = initialValues.toTypedArray()
        extendedValues = mutableMapOf()
        input.clear()
        relativeBase = 0
        pointerStart = 0
        isPaused = false
    }

    /**
     * Resets the [onOutput] function to its default value.
     */
    fun resetOutput() {
        onOutput = DEFAULT_OUTPUT
    }

    companion object {
        /**
         * The default function to be run when a program produces a value as output.
         */
        private val DEFAULT_OUTPUT: (BigInteger) -> Unit = { println(it) }

        /**
         * The default value to be assumed for any position in the program that isn't explicitly set.
         */
        private val DEFAULT_VALUE = BigInteger.ZERO

        /**
         * The divisor used to separate the opcode and parameter modes of an operation value.
         */
        private val OPCODE_MOD = BigInteger("100")
    }
}
