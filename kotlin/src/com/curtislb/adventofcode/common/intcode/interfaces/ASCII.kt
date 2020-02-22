package com.curtislb.adventofcode.common.intcode.interfaces

import com.curtislb.adventofcode.common.intcode.Intcode
import com.curtislb.adventofcode.common.range.BigIntegerRange
import java.io.File
import java.math.BigInteger

/**
 * An interface for an [Intcode] program that accepts ASCII code values as input and may produce output corresponding to
 * printable ASCII characters.
 *
 * @param intcode The [Intcode] program to be controlled through this interface.
 * @param showAsciiOutput If `true`, prints all output values from the program, with values within the normal ASCII
 *  range `0..127` first converted to their corresponding characters. If `false`, only prints output values that are
 *  outside the normal ASCII range.
 */
class ASCII(private val intcode: Intcode, private val showAsciiOutput: Boolean = false) {
    /**
     * An optional function to be run for each output value produced by the program.
     *
     * @see [Intcode.onOutput]
     */
    var processOutput: ((output: BigInteger) -> Unit)? = null

    init {
        intcode.onOutput = { output ->
            showOutput(output)
            processOutput?.invoke(output)
        }
    }

    /**
     * An interface for an [Intcode] program that accepts ASCII code values as input and may produce output
     * corresponding to printable ASCII characters.
     *
     * @param file A file containing the [Intcode] program to be controlled through this interface.
     * @param showAsciiOutput If `true`, prints all output values from the program, with values within the normal ASCII
     *  range `0..127` first converted to their corresponding characters. If `false`, only prints output values that are
     *  outside the normal ASCII range.
     */
    constructor(file: File, showAsciiOutput: Boolean = false) : this(Intcode(file), showAsciiOutput)

    /**
     * Whether the program has finished.
     *
     * @see [Intcode.isDone]
     */
    val isDone: Boolean
        get() = intcode.isDone

    /**
     * Whether the program has paused.
     *
     * @see [Intcode.isPaused]
     */
    val isPaused: Boolean
        get() = intcode.isPaused

    /**
     * Updates the [value] stored at [position] in the program.
     *
     * @see [Intcode.set]
     */
    operator fun set(position: Int, value: BigInteger) { intcode[position] = value }

    /**
     * Restores the program to its starting state.
     *
     * @see [Intcode.reset]
     */
    fun reset() { intcode.reset() }

    /**
     * Runs the program by processing operations one at a time until a stopping condition is reached.
     *
     * @see [Intcode.run]
     */
    fun run() { intcode.run() }

    /**
     * Converts [input] to an ASCII sequence and sends it as input to the program, followed by a newline if
     * [sendNewline] is `true`.
     *
     * @see [Intcode.sendInput]
     */
    fun sendInput(input: String, sendNewline: Boolean = true) {
        intcode.sendInput(input.map { it.toInt().toBigInteger() }.asSequence())
        if (sendNewline) {
            intcode.sendInput(NEWLINE_CODE)
        }
    }

    /**
     * Prints [output] followed by a newline if it is outside the normal ASCII range. Otherwise, if [showAsciiOutput] is
     * `true`, prints the ASCII character corresponding to [output].
     */
    private fun showOutput(output: BigInteger) {
        when {
            output !in ASCII_CODE_RANGE -> println(output)
            showAsciiOutput -> print(output.toInt().toChar())
        }
    }

    companion object {
        /**
         * A range of all valid ASCII code values.
         */
        val ASCII_CODE_RANGE: BigIntegerRange = BigIntegerRange(0..127)

        /**
         * The ASCII value of the newline character.
         */
        val NEWLINE_CODE: BigInteger = BigInteger.TEN
    }
}

