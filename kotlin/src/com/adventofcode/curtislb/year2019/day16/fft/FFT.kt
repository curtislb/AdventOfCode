package com.adventofcode.curtislb.year2019.day16.fft

import java.lang.IllegalStateException
import kotlin.math.abs

/**
 * TODO
 */
class FFT(private val inputSignal: List<Int>) {
    /**
     * TODO
     */
    var signal: List<Int> = inputSignal
        private set

    /**
     * TODO
     */
    fun run(phases: Int) {
        for (i in 1..phases) {
            signal = signal.mapIndexed { index, _ -> getTransformedSignalValue(index) }
        }
    }

    /**
     * TODO
     */
    fun reset() {
        signal = inputSignal
    }

    /**
     * TODO
     */
    private fun getTransformedSignalValue(index: Int): Int {
        val patternIterator = generatePattern(index).iterator()
        patternIterator.next()
        val transformedValueSum = signal.fold(0L) { total: Long, value: Int -> total + patternIterator.next() * value }
        return (abs(transformedValueSum) % 10L).toInt()
    }

    /**
     * TODO
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
