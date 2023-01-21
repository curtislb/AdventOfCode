package com.curtislb.adventofcode.year2021.day23.amphipod

/**
 * A type of amphipod that lives in a [Burrow].
 *
 * @param symbol A character that uniquely identifies this type of amphipod.
 * @param roomIndex The index of the room in a [Burrow] that belongs to amphipods of this type.
 * @param energyPerStep The energy required for an amphipod of this type to move one step.
 */
enum class Amphipod(val symbol: Char, val roomIndex: Int, val energyPerStep: Long) {
    AMBER(symbol = 'A', roomIndex = 0, energyPerStep = 1L),
    BRONZE(symbol = 'B', roomIndex = 1, energyPerStep = 10L),
    COPPER(symbol = 'C', roomIndex = 2, energyPerStep = 100L),
    DESERT(symbol = 'D', roomIndex = 3, energyPerStep = 1000L);

    companion object {
        /**
         * Returns the [Amphipod] type that corresponds to the given [char] symbol.
         *
         * @throws IllegalArgumentException If [char] does not correspond to a type of [Amphipod].
         */
        fun fromChar(char: Char): Amphipod =
            Amphipod.values().find { it.symbol == char }
                ?: throw IllegalArgumentException("Invalid amphipod character: $char")
    }
}
