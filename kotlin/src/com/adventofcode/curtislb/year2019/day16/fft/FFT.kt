package com.adventofcode.curtislb.year2019.day16.fft

import kotlin.math.abs

/**
 * Implementation of the "Flawed Frequency Transmission" (FFT) algorithm for transforming an arbitrary input signal.
 * @param inputSignal A [List] of integer digits representing the signal to be transformed.
 */
class FFT(private val inputSignal: List<Int>) {
    /**
     * A [List] of integer digits representing the current transformed signal.
     */
    var signal: List<Int> = inputSignal
        private set

    /**
     * Transforms [signal] by applying the FFT transformation to it a given number of times.
     * @param phases The number of times in succession that the FFT transformation should be applied.
     */
    fun run(phases: Int = 1) {
        for (phase in 1..phases) {
            signal = signal.mapIndexed { index, _ -> getTransformedSignalValue(index) }
        }
    }

    /**
     * Restores [signal] to its original state before the FFT algorithm was run.
     */
    fun reset() {
        signal = inputSignal
    }

    /**
     * Gives the new signal value for a given position after a single phase of the FFT algorithm.
     * @param index The 0-indexed position in [signal] for which to calculate the new value.
     * @return The new value for position [index] in [signal] after applying the FFT transformation.
     */
    private fun getTransformedSignalValue(index: Int): Int {
        val patternIterator = generatePattern(index).iterator()
        patternIterator.next()
        val transformedValueSum = signal.fold(0L) { total, value -> total + patternIterator.next() * value }
        return (abs(transformedValueSum) % 10L).toInt()
    }

    /**
     * Produces a sequence of multipliers used by the FFT algorithm to transform a signal.
     * @param signalIndex The 0-indexed signal position for which to generate pattern values.
     * @return An infinite [Sequence] of integer values used to transform the signal value at position [signalIndex].
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
}
