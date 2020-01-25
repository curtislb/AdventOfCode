package com.adventofcode.curtislb.year2019.day18.vault.space

/**
 * TODO
 */
abstract class Space(val symbol: Char) {
    companion object {
        /**
         * TODO
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
