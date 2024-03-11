package com.curtislb.adventofcode.year2023.day19.workflow

/**
 * A type of category in which each [MachinePart] is rated.
 *
 * @property char A character that uniquely identifies the category.
 */
enum class Category(val char: Char) {
    /**
     * A part category described as "extremely cool looking".
     */
    EXTREME('x'),

    /**
     * A part category described as "musical (it makes a noise when you hit it)".
     */
    MUSICAL('m'),

    /**
     * A part category described as "aerodynamic".
     */
    AERO('a'),

    /**
     * A part category described as "shiny".
     */
    SHINY('s');

    override fun toString(): String = char.toString()

    companion object {
        /**
         * Returns the [Category] that corresponds to the given [char].
         *
         * @throws IllegalArgumentException If [char] has no corresponding category.
         */
        fun fromChar(char: Char): Category {
            return entries.find { it.char == char }
                ?: throw IllegalArgumentException("Invalid enum char: $char")
        }
    }
}
