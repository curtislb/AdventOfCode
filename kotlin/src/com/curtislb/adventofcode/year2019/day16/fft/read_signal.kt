package com.curtislb.adventofcode.year2019.day16.fft

import com.curtislb.adventofcode.common.io.forEachChar
import com.curtislb.adventofcode.common.math.toDigit
import java.io.File

/**
 * Returns the contents of this file as an input signal for the [FFT] algorithm.
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
