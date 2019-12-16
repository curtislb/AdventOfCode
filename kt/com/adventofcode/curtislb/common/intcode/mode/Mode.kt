package com.adventofcode.curtislb.common.intcode.mode

import com.adventofcode.curtislb.common.intcode.Intcode
import com.adventofcode.curtislb.common.math.digit

/**
 * Constants and utilities related to parameter modes recognized by an Intcode program. These modes indicate how a given
 * parameter's value should be interpreted during an operation and are as follows:
 * - Mode 0 (default): Position mode. This parameter's value refers to a 0-indexed position in the Intcode program.
 *   This mode is assumed for any parameter indicating where a value should be stored.
 * - Mode 1: Immediate mode. This parameter's value should be used directly as an argument.
 */
object Mode {
    private const val POSITION = 0
    private const val IMMEDIATE = 1

    /**
     * Converts the value of a parameter according to a given parameter mode.
     *
     * @param intcode The currently running Intcode program.
     * @param cursor The 0-indexed position of the current operation in [intcode].
     * @param param The parameter value to be converted.
     * @param paramIndex The 0-indexed ordinal number of [param] for the current operation.
     * @return The result of interpreting [param] according to its mode, as given by the current operation.
     *
     * @throws IllegalArgumentException If an unknown parameter mode is encountered.
     */
    fun apply(intcode: Intcode, cursor: Int, param: Int, paramIndex: Int = 0): Int {
        val mode = (intcode[cursor] / 100).digit(paramIndex)
        return applyInternal(mode, intcode, param)
    }

    /**
     * Converts the value of multiple parameters for an operation according to their parameter modes.
     *
     * @param intcode The currently running Intcode program.
     * @param cursor The 0-indexed position of the current operation in [intcode].
     * @param params The vararg list of parameter values to be converted.
     * @return A [List] containing each value in [params] interpreted according to its mode.
     *
     * @throws IllegalArgumentException If an unknown parameter mode is encountered.
     */
    fun applyAll(intcode: Intcode, cursor: Int, vararg params: Int): List<Int> {
        val modes = getModes(intcode, cursor)
        return params.mapIndexed { index, param -> applyInternal(modes.digit(index), intcode, param) }
    }

    /**
     * Checks that the [Mode] for the given parameter is [Mode.POSITION].
     *
     * @param intcode The currently running Intcode program.
     * @param cursor The 0-indexed position of the current operation in [intcode].
     * @param paramIndex The 0-indexed ordinal number of the parameter whose mode should be checked.
     *
     * @throws IllegalArgumentException If the mode for the given parameter is not [Mode.POSITION].
     */
    fun checkIsPosition(intcode: Intcode, cursor: Int, paramIndex: Int) {
        val modes = getModes(intcode, cursor)
        val mode = modes.digit(paramIndex)
        if (mode != POSITION) {
            throw IllegalArgumentException("Expecting parameter mode $POSITION, but got $mode")
        }
    }

    /**
     * Converts the value of a parameter according to a given parameter mode.
     *
     * @param mode An integer representing the parameter mode to apply.
     * @param intcode The currently running Intcode program.
     * @param param The parameter value to be converted.
     * @return The result of interpreting [param] according to [mode].
     *
     * @throws IllegalArgumentException If [mode] represents an unknown parameter mode.
     */
    private fun applyInternal(mode: Int, intcode: Intcode, param: Int): Int {
        return when (mode) {
            POSITION -> applyPosition(intcode, param)
            IMMEDIATE -> applyImmediate(param)
            else -> throw IllegalArgumentException("Unknown parameter mode $mode")
        }
    }

    /**
     * Produces an integer representing the parameter modes for a given operation.
     * @param intcode The currently running Intcode program.
     * @param cursor The 0-indexed position of the current operation in [intcode].
     * @return An [Int] where the `n`th least significant digit represents the mode for the `n`th parameter.
     */
    private fun getModes(intcode: Intcode, cursor: Int) = intcode[cursor] / 100
}
