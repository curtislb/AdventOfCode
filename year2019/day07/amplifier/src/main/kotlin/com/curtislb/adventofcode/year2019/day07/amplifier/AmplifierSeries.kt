package com.curtislb.adventofcode.year2019.day07.amplifier

import com.curtislb.adventofcode.common.iteration.permutations
import com.curtislb.adventofcode.common.intcode.Intcode
import java.io.File
import java.math.BigInteger

/**
 * A collection of signal amplifiers wired in series, such that the output of each becomes input for
 * the next amplifier.
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
     * Returns the maximum signal that can be produced by this amplifier series when [phaseSettings]
     * are provided to the amplifiers in any order.
     *
     * @throws IllegalArgumentException If the number of [phaseSettings] does not match the number
     *  of amplifiers.
     */
    fun findMaxSignal(phaseSettings: List<BigInteger>): BigInteger {
        checkPhaseSettings(phaseSettings)

        // Keep track of maximum output from the last amplifier
        var maxSignal = BigInteger.ZERO
        amplifiers.last().onOutput = { maxSignal = maxOf(maxSignal, it) }

        try {
            for (settings in phaseSettings.permutations()) {
                // Send phase settings and initial input
                amplifiers.forEachIndexed { index, amplifier ->
                    amplifier.sendInput(settings[index])
                }
                amplifiers[0].sendInput(BigInteger.ZERO)

                // Run each amplifier program in sequence
                for (amplifier in amplifiers) {
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
     * Returns the maximum signal that can be produced by this amplifier series when it is wired to
     * create a feedback loop and [phaseSettings] are provided to the amplifiers in any order.
     *
     * @throws IllegalArgumentException If the number of [phaseSettings] does not match the number
     *  of amplifiers.
     */
    fun findMaxSignalWithFeedback(phaseSettings: List<BigInteger>): BigInteger {
        checkPhaseSettings(phaseSettings)

        // Send output from the last amplifier to the first, keeping track of the max final output
        var maxSignal = BigInteger.ZERO
        amplifiers.last().onOutput = { output ->
            if (amplifiers[0].isDone) {
                maxSignal = maxOf(maxSignal, output)
            } else {
                amplifiers[0].sendInput(output)
            }
        }

        try {
            for (settings in phaseSettings.permutations()) {
                // Send phase settings and initial input
                amplifiers.forEachIndexed { index, amplifier ->
                    amplifier.resetState()
                    amplifier.sendInput(settings[index])
                }
                amplifiers[0].sendInput(BigInteger.ZERO)

                // Continue running amplifier programs until all have halted
                while (!amplifiers.all { it.isDone }) {
                    for (amplifier in amplifiers) {
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
     * Validates the given [phaseSettings] for this amplifier series.
     *
     * @throws IllegalArgumentException If the number of [phaseSettings] does not match the number
     *  of amplifiers.
     */
    private fun checkPhaseSettings(phaseSettings: Collection<BigInteger>) {
        require(phaseSettings.size == amplifiers.size) {
            "Expected ${amplifiers.size} phase settings, but got ${phaseSettings.size}."
        }
    }

    /**
     * Restores all amplifiers in this series to their original states, immediately after
     * initialization.
     */
    private fun resetAmplifiers() {
        for (amplifier in amplifiers) {
            amplifier.resetState()
        }
        amplifiers.last().resetOutput()
    }
}
