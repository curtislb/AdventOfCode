package com.curtislb.adventofcode.common.intcode.interfaces

import com.curtislb.adventofcode.common.intcode.Intcode
import com.curtislb.adventofcode.common.range.BigIntegerRange
import java.io.File
import java.math.BigInteger

/**
 * An interface for an [Intcode] program that accepts ASCII code values as input and may produce
 * output corresponding to printable ASCII characters.
 *
 * @param intcode The [Intcode] program to be controlled through this interface.
 * @param showAsciiOutput If `true`, prints all output values from the program, with values within
 *  the normal ASCII range `0..127` first converted to their corresponding characters. If
 *  `false`, only prints output values that are outside the normal ASCII range.
 * @param processOutput An optional function to run on each output value from the program.
 */
class Ascii private constructor(
    private val intcode: Intcode,
    private val showAsciiOutput: Boolean,
    var processOutput: ((output: BigInteger, isAscii: Boolean) -> Unit)? = null
) {
    init {
        intcode.onOutput = { output ->
            val isAscii = output in ASCII_CODE_RANGE
            if (showAsciiOutput && isAscii) {
                print(output.toInt().toChar())
            }
            processOutput?.invoke(output, isAscii)
        }
    }

    /**
     * Whether the program has finished.
     *
     * @see [Intcode.isDone]
     */
    val isDone: Boolean get() = intcode.isDone

    /**
     * Whether the program has paused.
     *
     * @see [Intcode.isPaused]
     */
    val isPaused: Boolean get() = intcode.isPaused

    /**
     * An interface for an [Intcode] program that accepts ASCII code values as input and may produce
     * output corresponding to printable ASCII characters.
     *
     * @param file A file containing the [Intcode] program to be controlled through this interface.
     * @param showAsciiOutput If `true`, prints all output values from the program, with values
     *  within the normal ASCII range `0..127` first converted to their corresponding characters.
     *  If `false`, only prints output values that are outside the normal ASCII range.
     * @param processOutput An optional function to run on each output value from the program.
     */
    constructor(
        file: File,
        showAsciiOutput: Boolean = false,
        processOutput: ((output: BigInteger, isAscii: Boolean) -> Unit)? = null
    ) : this(Intcode(file), showAsciiOutput, processOutput)

    /**
     * An interface for an [Intcode] program that accepts ASCII code values as input and may
     * produce output corresponding to printable ASCII characters.
     *
     * @param programString A string representation of the program, consisting of comma-separated
     *  integer values.
     * @param showAsciiOutput If `true`, prints all output values from the program, with values
     *  within the normal ASCII range `0..127` first converted to their corresponding characters.
     *  If `false`, only prints output values that are outside the normal ASCII range.
     * @param processOutput An optional function to run on each output value from the program.
     */
    constructor(
        programString: String,
        showAsciiOutput: Boolean = false,
        processOutput: ((output: BigInteger, isAscii: Boolean) -> Unit)? = null
    ) : this(Intcode(programString), showAsciiOutput, processOutput)

    /**
     * Updates the [value] stored at [position] in the program.
     *
     * @see [Intcode.set]
     */
    operator fun set(position: Int, value: BigInteger) {
        intcode[position] = value
    }

    /**
     * Converts [input] to an ASCII sequence and sends it as input to the program, followed by a
     * newline if [sendNewline] is `true`.
     *
     * @see [Intcode.sendInput]
     */
    fun sendInput(input: String, sendNewline: Boolean = true) {
        intcode.sendInput(input.map { it.code.toBigInteger() }.asSequence())
        if (sendNewline) {
            intcode.sendInput(NEWLINE_CODE)
        }
    }

    /**
     * Runs the program by processing operations in sequence until a stopping condition is reached.
     *
     * @see [Intcode.run]
     */
    fun run() {
        intcode.run()
    }

    /**
     * Restores the program to its starting state.
     *
     * @see [Intcode.resetState]
     */
    fun resetState() {
        intcode.resetState()
    }

    companion object {
        /**
         * A range containing all valid ASCII code values.
         */
        val ASCII_CODE_RANGE = BigIntegerRange(0, 127)

        /**
         * The ASCII value of the newline character.
         */
        val NEWLINE_CODE: BigInteger = BigInteger.TEN
    }
}
