package com.curtislb.adventofcode.year2023.day20.pulse

/**
 * Abstract type for a module that communicates with others by sending and receiving [Pulse]s.
 *
 * @property name A name that uniquely defines the module.
 * @property destinations A list of names of other modules to which this module sends pulses.
 */
sealed class PulseModule(val name: String, val destinations: List<String>) {
    /**
     * A string prefix that uniquely identifies the module type.
     */
    abstract val prefix: String

    /**
     * Handles the specified [pulse], which was sent by a module with the given [sourceName], and
     * possibly returns a new pulse that should be sent to all [destinations] of this module.
     */
    abstract fun processPulse(sourceName: String, pulse: Pulse): Pulse?

    override fun toString(): String = "$prefix$name -> ${destinations.joinToString()}"

    companion object {
        /**
         * A regex used to parse the prefix, name, and destinations of a pulse module.
         */
        private val MODULE_REGEX = Regex("""(\W?)(\w+) -> (.+)""")

        /**
         * Returns a [PulseModule] with the [prefix], [name], and [destinations] specified by the
         * given [string].
         *
         * The [string] must have the format `$prefix$name -> $destinationsString`, where
         * `destinationsString` is a comma-and-space separated list of [destinations].
         *
         * @throws IllegalArgumentException If [string] is not formatted correctly.
         */
        fun fromString(string: String): PulseModule {
            // Parse values from the input string
            val matchResult = MODULE_REGEX.matchEntire(string)
            require(matchResult != null) { "Malformed module string: $string" }
            val (prefix, name, destinationsString) = matchResult.destructured
            val destinations = destinationsString.split(", ")

            // Find and instantiate the concrete type that matches the prefix
            return when (prefix) {
                ConjunctionModule.PREFIX -> ConjunctionModule(name, destinations)
                FlipFlopModule.PREFIX -> FlipFlopModule(name, destinations)
                PlainModule.PREFIX -> PlainModule(name, destinations)
                else -> throw IllegalArgumentException("Invalid module prefix: $prefix")
            }
        }
    }
}
