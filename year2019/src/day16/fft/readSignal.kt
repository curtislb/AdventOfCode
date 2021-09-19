package com.curtislb.adventofcode.year2019.day16.fft

import com.curtislb.adventofcode.common.io.forEachChar
import com.curtislb.adventofcode.common.math.toDigit
import java.io.File

/**
 * Returns the contents of this file as an input signal for the [FftAlgorithm] algorithm.
 */
fun File.readSignal(): List<Int> {
    return mutableListOf<Int>().apply {
        forEachChar { char ->
            if (char.isDigit()) {
                add(char.toDigit())
            }
        }
    }
}
