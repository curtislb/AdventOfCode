package com.curtislb.adventofcode.year2023.day14.dish

/**
 * A type of rock (or empty space) that may be present on a [DishPlatform].
 *
 * @property symbol A character that uniquely identifies the type of rock.
 */
enum class Rock(val symbol: Char) {
    /**
     * A rounded rock. This rock may move when the platform is tilted.
     */
    ROUND('O'),

    /**
     * A cube-shaped rock. This rock will not move when the platform is tilted.
     */
    CUBE('#'),

    /**
     * An empty space, which contains no rock.
     */
    NONE('.');

    override fun toString(): String = symbol.toString()

    companion object {
        /**
         * Returns the [Rock] that corresponds to the given [char].
         *
         * @throws IllegalArgumentException If [char] has no corresponding rock.
         */
        fun fromChar(char: Char): Rock {
            return entries.firstOrNull { it.symbol == char }
                ?: throw IllegalArgumentException("Invalid rock char: $char")
        }
    }
}
