package com.curtislb.adventofcode.year2023.day02.cubes

import com.curtislb.adventofcode.common.collection.Counter

/**
 * Description of a game consisting of rounds in which cubes of various colors are drawn from a bag
 * and shown to an observer before being placed back into the bag.
 *
 * @property id An integer ID that uniquely defines the game.
 * @property rounds A list of counts, representing how many cubes of each color were shown during
 *  each round of the game.
 *
 * @constructor Creates a new instance of [CubeGame] with the given [id] and [rounds].
 */
class CubeGame(val id: Int, private val rounds: List<Counter<String>>) {

    /**
     * Returns `true` if the game could have been played with a bag that contains at most the number
     * of cubes of each color specified in [maxColorCounts].
     */
    fun isPossible(maxColorCounts: Counter<String>): Boolean = rounds.all { it in maxColorCounts }

    /**
     * Returns a counter of the fewest number of cubes of each color that could have been in the bag
     * for the game to be possible.
     */
    fun findMinPossibleColorCounts(): Counter<String> {
        val minColorCounts = Counter<String>()
        for (roundColorCounts in rounds) {
            minColorCounts.takeLarger(roundColorCounts)
        }
        return minColorCounts
    }

    companion object {
        /**
         * A regex used to parse information about a game.
         */
        private val GAME_REGEX = Regex("""Game (\d+): (.*)""")

        /**
         * Returns a [CubeGame] with information read from the given [string].
         *
         * The [string] must have the format `"Game $id: $round1; $round2; ... $roundN"`, where each
         * round consists of a comma-separated list of color counts, such as `"red 12, blue 5"`.
         *
         * @throws IllegalArgumentException If [string] is not formatted correctly.
         */
        fun fromString(string: String): CubeGame {
            val matchResult = GAME_REGEX.matchEntire(string)
            require(matchResult != null) { "Malformed game string: $string" }

            val (idString, roundsString) = matchResult.destructured
            val id = idString.toInt()
            val rounds = roundsString.split(";").map { roundCountsString ->
                // Parse the number of cubes of each color shown during the round
                val colorCounts = Counter<String>()
                roundCountsString.split(",").forEach { colorCountString ->
                    val (countString, color) = colorCountString.trim().split(" ")
                    colorCounts[color] += countString.toLong()
                }
                colorCounts
            }
            return CubeGame(id, rounds)
        }
    }
}
