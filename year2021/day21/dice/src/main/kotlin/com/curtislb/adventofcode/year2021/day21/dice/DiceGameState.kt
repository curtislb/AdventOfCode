package com.curtislb.adventofcode.year2021.day21.dice

import com.curtislb.adventofcode.common.io.forEachLineIndexed
import java.io.File

/**
 * The current state of a dice-rolling game involving one or more players.
 *
 * @param players A list of the current states of all players in the game.
 * @param nextPlayer Index of the player whose turn is up next.
 */
data class DiceGameState(val players: List<Player>, val nextPlayer: Int = 0) {
    /**
     * Returns the index of the first player in [players] whose score is at least [winningScore],
     * or [NO_WINNER] if no player has the requisite score.
     */
    fun getWinner(winningScore: Int): Int = players.indexOfFirst { it.score >= winningScore }

    /**
     * Returns the new game state after the current [nextPlayer] has taken their turn, having rolled
     * the given [rollTotal].
     *
     * A player takes their turn by moving forward a number of spaces equal to [rollTotal] on the
     * circular board and scoring points equal to 1 + the index of the space they land on. The next
     * numbered player in order then becomes the [nextPlayer] to take a turn.
     */
    fun nextTurn(rollTotal: Int): DiceGameState {
        val newSpace = (players[nextPlayer].space + rollTotal) % SPACES_COUNT
        val newScore = players[nextPlayer].score + newSpace + 1
        val newPlayers = players.mapIndexed { index, player ->
            if (index == nextPlayer) Player(newSpace, newScore) else player
        }
        val newNextPlayer = (nextPlayer + 1) % players.size
        return DiceGameState(newPlayers, newNextPlayer)
    }

    companion object {
        /**
         * The value returned by [getWinner] if no player has won the game.
         */
        const val NO_WINNER = -1

        /**
         * The total number of spaces on the cyclical game board.
         */
        private const val SPACES_COUNT = 10

        /**
         * Regex used to parse the starting position for a given player.
         */
        private val STARTING_POSITION_REGEX =
            Regex("""\s*Player\s+(\d+)\s+starting\s+position:\s+(\d+)\s*""")

        /**
         * Returns a [DiceGameState] with players' starting positions read from the given [file].
         *
         * The [file] must have the following format:
         *
         * ```
         * Player 1 starting position: $position1
         * Player 2 starting position: $position2
         * ...
         * ```
         *
         * @throws IllegalArgumentException If [file] is not formatted correctly.
         */
        fun fromFile(file: File): DiceGameState {
            val players = mutableListOf<Player>()
            file.forEachLineIndexed { lineIndex, line ->
                // Parse the current line
                val matchResult = STARTING_POSITION_REGEX.matchEntire(line)
                require(matchResult != null) { "Incorrect line format: $line" }
                val (playerNumberString, positionString) = matchResult.destructured

                // Check that the player and line number match
                val playerNumber = playerNumberString.toInt()
                require(playerNumber == lineIndex + 1) {
                    "Player number must match line number: $playerNumber != ${lineIndex + 1}"
                }

                // Check that the starting position is valid
                val position = positionString.toInt()
                require(position in 1..SPACES_COUNT) { "Invalid starting position: $position" }

                // Set the player's space index based on their position
                val player = Player(space = position - 1)
                players.add(player)
            }

            return DiceGameState(players)
        }
    }
}