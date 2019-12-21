package com.adventofcode.curtislb.year2019.day07.amplifier

import com.adventofcode.curtislb.common.intcode.Intcode
import java.io.File

/**
 * TODO
 */
fun createAmplifierSeries(file: File, count: Int): Array<Intcode> {
    val programString = file.readText().trim()
    val amplifiers = Array(count) { Intcode(programString) }
    for (i in 0 until amplifiers.lastIndex) {
        amplifiers[i].onOutput = { amplifiers[i + 1].sendInput(it) }
    }
    return amplifiers
}
