package com.curtislb.adventofcode.year2023.day20.pulse

/**
 * A single [pulse] sent by a [source] module to a [destination] module.
 */
data class PulseSignal(val source: String, val pulse: Pulse, val destination: String) {
    override fun toString(): String = "$source -${pulse.name.lowercase()}-> $destination"
}
