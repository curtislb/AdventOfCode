package com.curtislb.adventofcode.year2019.day07.amplifier

import com.curtislb.adventofcode.common.collection.permutations
import com.curtislb.adventofcode.common.intcode.Intcode
import java.io.File
import java.math.BigInteger

/**
 * A collection of signal amplifiers wired in series, such that the output of each becomes input for the next amplifier.
 *
 * @param file A file containing the [Intcode] program to be run by each of the amplifiers.
 * @param count The number of amplifiers to be wired in series.
 *
 * @throws IllegalArgumentException If [count] is less than 1.
 */
class AmplifierSeries(file: File, count: Int) {
    init {
        require(count > 0) { "Count must be at least 1: $count" }
    }

    /**
     * An array containing the Intcode programs for all amplifiers in order.
     */
    private val amplifiers: Array<Intcode> = Array(count) { Intcode(file.readText().trim()) }

    init {
        for (i in 0 until amplifiers.lastIndex) {
            amplifiers[i].onOutput = { amplifiers[i + 1].sendInput(it) }
        }
    }

    /**
     * Returns the maximum signal that can be produced by this amplifier series when [phaseSettings] are provided to the
     * amplifiers in any order.
     *
     * @throws IllegalArgumentException If the number of [phaseSettings] does not match the number of amplifiers.
     */
    fun findMaxSignal(phaseSettings: Collection<BigInteger>): BigInteger {
        require(phaseSettings.size == amplifiers.size) {
            "The number of phase settings (${phaseSettings.size}) must match the amplifier count (${amplifiers.size})."
        }

        // Keep track of maximum output from the last amplifier.
        var maxSignal = BigInteger.ZERO
        amplifiers.last().onOutput = { maxSignal = maxSignal.coerceAtLeast(it) }

        try {
            // Try all permutations of phase settings.
            phaseSettings.permutations().forEach { settings ->
                amplifiers.forEachIndexed { index, amplifier -> amplifier.sendInput(settings[index]) }
                amplifiers[0].sendInput(BigInteger.ZERO)
                amplifiers.forEach { amplifier ->
                    amplifier.run()
                    amplifier.resetState()
                }
            }
        } finally {
            resetAmplifiers()
        }

        return maxSignal
    }

    /**
     * Returns the maximum signal that can be produced by this amplifier series when it is wired to create a feedback
     * loop and [phaseSettings] are provided to the amplifiers in any order.
     *
     * @throws IllegalArgumentException If the number of [phaseSettings] does not match the number of amplifiers.
     */
    fun findMaxSignalWithFeedback(phaseSettings: Collection<BigInteger>): BigInteger {
        require(phaseSettings.size == amplifiers.size) {
            "The number of phase settings (${phaseSettings.size}) must match the amplifier count (${amplifiers.size})."
        }

        // Feed output from the last amplifier into the first, keeping track of the maximum final output.
        var maxSignal = BigInteger.ZERO
        amplifiers.last().onOutput = { output ->
            if (amplifiers[0].isDone) {
                maxSignal = maxSignal.coerceAtLeast(output)
            } else {
                amplifiers[0].sendInput(output)
            }
        }

        try {
            // Try all permutations of phase settings.
            phaseSettings.permutations().forEach { settings ->
                // Send phase settings and initial input.
                amplifiers.forEachIndexed { index, amplifier ->
                    amplifier.resetState()
                    amplifier.sendInput(settings[index])
                }
                amplifiers[0].sendInput(BigInteger.ZERO)

                // Continue running amplifier programs until all have halted.
                while (!amplifiers.all { it.isDone }) {
                    amplifiers.forEach { amplifier ->
                        if (!amplifier.isDone) {
                            amplifier.run()
                        }
                    }
                }
            }
        } finally {
            resetAmplifiers()
        }

        return maxSignal
    }

    /**
     * Restores all amplifiers in this series to their original states, immediately after initialization.
     */
    private fun resetAmplifiers() {
        amplifiers.forEach { it.resetState() }
        amplifiers.last().resetOutput()
    }
}
