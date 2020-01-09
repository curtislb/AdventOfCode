package com.adventofcode.curtislb.year2019.day07.amplifier

import com.adventofcode.curtislb.common.intcode.Intcode
import java.io.File

/**
 * Creates an amplifier configuration, where each amplifier's output is sent as input to the next one.
 * @param file A [File] containing an [Intcode] program to be run by all amplifiers.
 * @param count The number of amplifiers to be connected in series.
 * @return An [Array] of [count] [Intcode] programs representing amplifiers, where the output of each (except the last)
 *  is configured to be sent as input to the next program in order.
 */
fun createAmplifierSeries(file: File, count: Int): Array<Intcode> {
    val programString = file.readText().trim()
    val amplifiers = Array(count) { Intcode(programString) }
    for (i in 0 until amplifiers.lastIndex) {
        amplifiers[i].onOutput = { amplifiers[i + 1].sendInput(it) }
    }
    return amplifiers
}
