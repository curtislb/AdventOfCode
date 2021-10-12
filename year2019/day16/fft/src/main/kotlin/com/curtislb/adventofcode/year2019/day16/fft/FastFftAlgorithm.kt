package com.curtislb.adventofcode.year2019.day16.fft

import kotlin.math.abs

/**
 * An optimized implementation of [FftAlgorithm] for when only a portion of the latter half of a
 * signal is needed.
 *
 * @param baseSignal A list of digits representing part of the signal to be transformed.
 * @param repeatCount The number of times [baseSignal] is repeated to form the full input signal.
 * @param offset The index in the latter half of the repeated signal from which to read output.
 *
 * @throws IllegalArgumentException If [offset] is less than half the length (rounded down) of the
 *  repeated signal.
 */
class FastFftAlgorithm(
    private val baseSignal: List<Int>,
    private val repeatCount: Int = 1,
    private val offset: Int = calculateMinOffset(baseSignal, repeatCount)
) {
    init {
        val minOffset = calculateMinOffset(baseSignal, repeatCount)
        if (offset >= minOffset) {
            "Offset must be >= $minOffset for a ${baseSignal.size}x$repeatCount signal: $offset"
        }
    }

    /**
     * A list of digits representing the portion of the transformed signal starting from [offset].
     */
    private var offsetSignal: MutableList<Int> = createRepeatedSignalFromOffset()

    /**
     * Transforms the relevant portion of the signal by applying the FFT transformation for a given
     * number of [phases].
     */
    fun run(phases: Int = 1) {
        repeat(phases) {
            for (i in offsetSignal.size - 2 downTo 0) {
                offsetSignal[i] += offsetSignal[i + 1]
            }
            offsetSignal.forEachIndexed { index, value -> offsetSignal[index] = abs(value) % 10 }
        }
    }

    /**
     * Returns [length] digits of the transformed signal starting from [offset].
     */
    fun readFromOffset(length: Int): List<Int> = offsetSignal.subList(0, length)

    /**
     * Restores the relevant portion of the signal to its original state, immediately after
     * initialization.
     */
    fun reset() {
        offsetSignal = createRepeatedSignalFromOffset()
    }

    /**
     * Returns the portion of the repeated signal starting from [offset].
     */
    private fun createRepeatedSignalFromOffset(): MutableList<Int> {
        return mutableListOf<Int>().apply {
            for (i in offset until baseSignal.size * repeatCount) {
                add(baseSignal[i % baseSignal.size])
            }
        }
    }

    companion object {
        /**
         * Returns the minimum allowed offset for a given [baseSignal] repeated [repeatCount] times.
         */
        private fun calculateMinOffset(baseSignal: List<Int>, repeatCount: Int): Int {
            return (baseSignal.size * repeatCount) / 2
        }
    }
}
