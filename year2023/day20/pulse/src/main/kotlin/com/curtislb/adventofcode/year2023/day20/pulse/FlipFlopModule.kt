package com.curtislb.adventofcode.year2023.day20.pulse

/**
 * A [PulseModule] that alternates between "on" and "off" states.
 *
 * The specific behavior of this module is as follows:
 *
 * - If the module receives a high pulse, it is ignored and nothing happens.
 * - If the module receives a low pulse, it flips between on and off. If it was off, it turns on and
 *   sends a high pulse. If it was on, it turns off and sends a low pulse.
 *
 * @constructor Creates a new instance of [FlipFlopModule] with the given [name] and [destinations].
 */
class FlipFlopModule(name: String, destinations: List<String>) : PulseModule(name, destinations) {
    override val prefix: String = PREFIX

    private var isOn: Boolean = false

    override fun processPulse(sourceName: String, pulse: Pulse): Pulse? = when (pulse) {
        Pulse.HIGH -> null
        Pulse.LOW -> {
            isOn = !isOn
            if (isOn) Pulse.HIGH else Pulse.LOW
        }
    }

    companion object {
        /**
         * The prefix string for the [FlipFlopModule] type.
         */
        const val PREFIX = "%"
    }
}
