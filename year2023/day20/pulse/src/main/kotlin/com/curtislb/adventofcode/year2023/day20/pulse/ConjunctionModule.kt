package com.curtislb.adventofcode.year2023.day20.pulse

/**
 * A [PulseModule] that remembers the type of the most recent pulse received from each of its
 * connected input modules.
 *
 * The specific behavior of this module is as follows:
 *
 * - The module initially defaults to remembering a low pulse for each of its inputs.
 * - When the module receives a pulse, it first updates its memory for that input.
 * - Then, if the module remembers high pulses for all inputs, it sends a low pulse; otherwise, it
 *   sends a high pulse.
 *
 * @constructor Creates a new instance of [ConjunctionModule] with the given [name] and
 *  [destinations].
 */
class ConjunctionModule(
    name: String,
    destinations: List<String>
) : PulseModule(name, destinations) {

    override val prefix: String = PREFIX

    /**
     * A map from the name of each known input module to the remembered pulse for that module.
     */
    private val rememberedInputs: MutableMap<String, Pulse> = mutableMapOf()

    /**
     * The total number of high pulses remembered for all known input modules.
     */
    private var highPulseCount: Int = 0

    /**
     * Adds the module with the specified [sourceName] as a known input for this module and
     * remembers a low pulse for it. This has no effect if the module is already a known input.
     */
    fun addInput(sourceName: String) {
        if (sourceName !in rememberedInputs) {
            rememberedInputs[sourceName] = Pulse.LOW
        }
    }

    override fun processPulse(sourceName: String, pulse: Pulse): Pulse {
        val lastPulse = rememberedInputs[sourceName] ?: error("Unknown input name: $sourceName")
        if (lastPulse != pulse) {
            when (pulse) {
                Pulse.HIGH -> highPulseCount++
                Pulse.LOW -> highPulseCount--
            }
        }
        rememberedInputs[sourceName] = pulse

        return if (highPulseCount == rememberedInputs.size) Pulse.LOW else Pulse.HIGH
    }

    companion object {
        /**
         * The prefix string for the [ConjunctionModule] type.
         */
        const val PREFIX = "&"
    }
}
