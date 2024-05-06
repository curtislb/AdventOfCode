package com.curtislb.adventofcode.year2023.day20.pulse

/**
 * A [PulseModule] that sends a copy of each pulse it receives.
 *
 * @constructor Creates a new instance of [PlainModule] with the given [name] and [destinations].
 */
class PlainModule(name: String, destinations: List<String>) : PulseModule(name, destinations) {
    override val prefix: String = PREFIX

    override fun processPulse(sourceName: String, pulse: Pulse): Pulse = pulse

    companion object {
        /**
         * The prefix string for the [PlainModule] type.
         */
        const val PREFIX = ""
    }
}
