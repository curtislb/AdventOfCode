package com.curtislb.adventofcode.year2023.day12.spring

/**
 * A condition for spring that may appear in a [SpringRecord].
 *
 * @property symbol A character that uniquely identifies the condition.
 */
enum class Condition(val symbol: Char) {
    /**
     * The spring is known to be broken.
     */
    BROKEN('#'),

    /**
     * The spring is known to be operational.
     */
    OPERATIONAL('.'),

    /**
     * The spring's condition is unknown.
     */
    UNKNOWN('?');

    override fun toString(): String = symbol.toString()

    companion object {
        /**
         * Returns a [Condition] corresponding to the given symbol [char].
         *
         * @throws IllegalArgumentException If [char] has no corresponding [Condition].
         */
        fun fromChar(char: Char): Condition {
            return entries.firstOrNull { it.symbol == char }
                ?: throw IllegalArgumentException("Invalid condition symbol: $char")
        }
    }
}
