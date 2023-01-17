package com.curtislb.adventofcode.year2021.day08.display

import com.curtislb.adventofcode.common.number.testBit

/**
 * A pattern corresponding to signal wires on a seven-segment display.
 *
 * @param wires An integer with bit values representing the state of each wire.
 */
@JvmInline
value class SignalPattern(private val wires: Int) {
    /**
     * Returns the number of active wires for this pattern.
     */
    fun countWires(): Int = wires.countOneBits()

    /**
     * Checks if all active wires in [other] are also active in this pattern.
     */
    operator fun contains(other: SignalPattern): Boolean = wires or other.wires == wires

    /**
     * Returns a pattern combining active wires from this pattern and [other].
     */
    operator fun plus(other: SignalPattern): SignalPattern = SignalPattern(wires or other.wires)

    /**
     * Returns a pattern with active wires from this pattern that are not active in [other].
     */
    operator fun minus(other: SignalPattern): SignalPattern =
        SignalPattern(wires and other.wires.inv())

    override fun toString(): String = buildString {
        for (char in WIRE_CHARS) {
            if (wires.testBit(char - WIRE_CHARS.first)) {
                append(char)
            }
        }
    }

    companion object {
        /**
         * A signal pattern with no active wires.
         */
        val NONE: SignalPattern = SignalPattern(0)

        /**
         * Characters representing all valid wires.
         */
        private val WIRE_CHARS: CharRange = 'a'..'g'

        /**
         * Returns a pattern with active wires corresponding to the characters in [wiresString].
         *
         * @throws IllegalArgumentException If any character in [wiresString] has no corresponding
         *  wire.
         */
        fun fromString(wiresString: String): SignalPattern {
            val wires = wiresString.toCharArray().fold(0) { partialWires, char ->
                require(char in WIRE_CHARS) { "Invalid wire character: $char" }
                partialWires or (1 shl (char - WIRE_CHARS.first))
            }
            return SignalPattern(wires)
        }
    }
}
