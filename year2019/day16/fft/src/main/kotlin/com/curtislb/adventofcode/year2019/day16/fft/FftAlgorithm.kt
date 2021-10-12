package com.curtislb.adventofcode.year2019.day16.fft

import kotlin.math.abs

/**
 * Implementation of the "Flawed Frequency Transmission" (FFT) algorithm for transforming an
 * arbitrary signal.
 *
 * @param inputSignal A list of digits representing the signal to be transformed.
 */
class FftAlgorithm(private val inputSignal: List<Int>) {
    /**
     * A list of digits representing the transformed signal.
     */
    var signal: List<Int> = inputSignal
        private set

    /**
     * Transforms [signal] by applying the FFT transformation for a given number of [phases].
     */
    fun run(phases: Int = 1) {
        for (phase in 1..phases) {
            signal = signal.mapIndexed { index, _ -> transformedSignalValueAt(index) }
        }
    }

    /**
     * Returns the signal value at [index] given by applying the FFT transformation to [signal].
     */
    private fun transformedSignalValueAt(index: Int): Int {
        val patternIterator = generatePattern(index).iterator().apply { next() }
        val transformedValueSum =
            signal.fold(0L) { total, value -> total + patternIterator.next() * value }
        return (abs(transformedValueSum) % 10L).toInt()
    }

    /**
     * Returns the sequence of coefficients used by the FFT algorithm to transform the signal value
     * at [signalIndex].
     */
    private fun generatePattern(signalIndex: Int): Sequence<Int> = sequence {
        var patternIndex = 0
        while (true) {
            val patternValue = when (patternIndex) {
                0, 2 -> 0
                1 -> 1
                3 -> -1
                else -> throw IllegalStateException("Invalid pattern index: $patternIndex")
            }
            for (i in 0..signalIndex) {
                yield(patternValue)
            }
            patternIndex = (patternIndex + 1) % 4
        }
    }

    /**
     * Restores [signal] to its original state, immediately after initialization.
     */
    fun reset() {
        signal = inputSignal
    }
}
