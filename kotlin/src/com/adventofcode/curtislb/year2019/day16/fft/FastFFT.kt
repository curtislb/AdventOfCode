package com.adventofcode.curtislb.year2019.day16.fft

import kotlin.math.abs

/**
 * An optimized implementation of the [FFT] algorithm for when only a portion of the latter half of a signal is needed.
 * @param baseSignal A [List] of integer digits representing part of the signal to be transformed.
 * @param repeatCount The number of times [baseSignal] is repeated to form the input signal to the FFT algorithm.
 * @param offset The 0-indexed position in the latter half of the repeated signal from which output should be read.
 * @throws IllegalArgumentException If [offset] is less than half the length (rounded down) of the repeated signal.
 */
class FastFFT(
    private val baseSignal: List<Int>,
    private val repeatCount: Int = 1,
    private val offset: Int = getMinimumOffset(baseSignal, repeatCount)
) {

    /**
     * A [List] of integer digits representing the portion of the current transformed signal starting from [offset].
     */
    private var offsetSignal: MutableList<Int>

    init {
        val minOffset = getMinimumOffset(baseSignal, repeatCount)
        if (offset < minOffset) {
            throw IllegalArgumentException("Offset must be >= $minOffset for ${baseSignal.size} x $repeatCount signal")
        }
        offsetSignal = getRepeatedSignalFromOffset()
    }

    /**
     * Transforms the relevant portion of the signal by applying the FFT algorithm to it a number of times.
     * @param phases The number of times in succession that the FFT transformation should be applied.
     */
    fun run(phases: Int = 1) {
        for (phase in 1..phases) {
            for (i in offsetSignal.size - 2 downTo 0) {
                offsetSignal[i] += offsetSignal[i + 1]
            }
            offsetSignal.forEachIndexed { index, value -> offsetSignal[index] = abs(value) % 10 }
        }
    }

    /**
     * Restores the relevant portion of the signal to its original state before the FFT algorithm was run.
     */
    fun reset() {
        offsetSignal = getRepeatedSignalFromOffset()
    }

    /**
     * Produces part of the current transformed signal starting from [offset].
     * @param length The number of signal values that should be read.
     * @return A [List] of [length] digits representing a portion of the transformed signal starting from [offset].
     */
    fun readFromOffset(length: Int): List<Int> = offsetSignal.subList(0, length)

    /**
     * Creates the portion of the repeated signal starting from [offset].
     * @return A [List] of digits representing all values in the repeated signal starting from [offset].
     */
    private fun getRepeatedSignalFromOffset(): MutableList<Int> {
        val halfSignal = mutableListOf<Int>()
        for (i in offset until baseSignal.size * repeatCount) {
            halfSignal.add(baseSignal[i % baseSignal.size])
        }
        return halfSignal
    }

    private companion object {
        /**
         * Finds the minimum allowable offset for a given repeated signal.
         * @param baseSignal A [List] of integer digits representing part of the signal to be transformed.
         * @param repeatCount The number of times [baseSignal] is repeated to produce the full signal.
         * @return The minimum value that may be used as the signal offset for [FastFFT].
         */
        fun getMinimumOffset(baseSignal: List<Int>, repeatCount: Int): Int = (baseSignal.size * repeatCount) / 2
    }
}
