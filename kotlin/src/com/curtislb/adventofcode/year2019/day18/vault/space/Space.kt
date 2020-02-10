package com.curtislb.adventofcode.year2019.day18.vault.space

/**
 * A type of space that may appear on the grid of tunnels within the vault.
 *
 * @param symbol The character that is used to represent this space.
 */
abstract class Space(val symbol: Char) {
    companion object {
        /**
         * Returns the space corresponding to [char].
         *
         * @throws IllegalArgumentException If [char] has no corresponding space.
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
