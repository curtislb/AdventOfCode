package com.adventofcode.curtislb.year2019.day18.vault.space

/**
 * A type of space that may appear on the grid of tunnels in the vault.
 * @param symbol A [Char] symbol that is used to represent this [Space].
 */
abstract class Space(val symbol: Char) {
    companion object {
        /**
         * Converts a [Char] to its corresponding [Space].
         * @param char A [Char] representing a valid [Space].
         * @return The [Space] corresponding to [char].
         * @throws IllegalArgumentException If [char] has no corresponding [Space].
         */
        fun from(char: Char): Space {
            return when {
                char == OpenSpace.symbol -> OpenSpace
                char == WallSpace.symbol -> WallSpace
                char == EntranceSpace.symbol -> EntranceSpace
                char.isLowerCase() -> KeySpace(char)
                char.isUpperCase() -> DoorSpace(char)
                else -> throw IllegalArgumentException("Invalid space character: $char")
            }
        }
    }
}
