package com.adventofcode.curtislb.year2019.day16.fft

import com.adventofcode.curtislb.common.io.forEachChar
import com.adventofcode.curtislb.common.math.toDigit
import java.io.File

/**
 * Reads a file and interprets its contents as an input signal for the FFT algorithm.
 * @receiver A [File] containing a concatenated sequence of base-10 digits.
 * @return A [List] of integer digits representing a signal that can be processed by the FFT algorithm.
 */
fun File.readSignal(): List<Int> {
    val signal = mutableListOf<Int>()
    forEachChar { char ->
        if (char.isDigit()) {
            signal.add(char.toDigit())
        }
    }
    return signal
}
