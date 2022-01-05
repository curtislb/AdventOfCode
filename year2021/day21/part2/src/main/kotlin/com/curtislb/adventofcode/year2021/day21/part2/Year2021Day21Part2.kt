/*
--- Part Two ---

Now that you're warmed up, it's time to play the real game.

A second compartment opens, this time labeled Dirac dice. Out of it falls a single three-sided die.

As you experiment with the die, you feel a little strange. An informational brochure in the
compartment explains that this is a quantum die: when you roll it, the universe splits into multiple
copies, one copy for each possible outcome of the die. In this case, rolling the die always splits
the universe into three copies: one where the outcome of the roll was 1, one where it was 2, and one
where it was 3.

The game is played the same as before, although to prevent things from getting too far out of hand,
the game now ends when either player's score reaches at least 21.

Using the same starting positions as in the example above, player 1 wins in 444356092776315
universes, while player 2 merely wins in 341960390180808 universes.

Using your given starting positions, determine every possible outcome. Find the player that wins in
more universes; in how many universes does that player win?
*/

package com.curtislb.adventofcode.year2021.day21.part2

import com.curtislb.adventofcode.common.collection.Counter
import com.curtislb.adventofcode.common.collection.argmaxByOrNull
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2021, day 21, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 */
fun solve(inputPath: Path = Paths.get("..", "input", "input.txt")): Long {
    val players = mutableListOf<Player>()
    inputPath.toFile().forEachLine { line ->
        val space = line.substringAfter(':').trim().toInt() - 1
        players.add(Player(space))
    }

    val rollFrequencies = mapOf(
        3 to 1L,
        4 to 3L,
        5 to 6L,
        6 to 7L,
        7 to 6L,
        8 to 3L,
        9 to 1L
    )

    val stateCounts = Counter(mapOf(GameState(players) to 1L))
    var ongoingEntries = stateCounts.findOngoing()
    while (ongoingEntries.isNotEmpty()) {
        ongoingEntries.forEach { (state, count) ->
            stateCounts[state] -= count
            rollFrequencies.forEach { (roll, frequency) ->
                val updatedState = state.update(roll)
                stateCounts[updatedState] += count * frequency
            }
        }
        ongoingEntries = stateCounts.findOngoing()
    }

    val winCounts = LongArray(players.size)
    stateCounts.entriesWithPositiveCount.forEach { (state, count) ->
        winCounts[state.winningPlayer()] += count
    }

    return winCounts.maxOrNull() ?: 0
}

data class GameState(val players: List<Player>, val nextPlayer: Int = 0) {
    fun isFinished() = players.any { it.score >= WINNING_SCORE }

    fun winningPlayer(): Int =
        players.argmaxByOrNull { it.score } ?: throw IllegalStateException("Game has no players")

    fun update(roll: Int): GameState {
        val newPlayers = players.toMutableList().apply {
            this[nextPlayer] = this[nextPlayer].move(roll)
        }
        val newNextPlayer = (nextPlayer + 1) % players.size
        return GameState(newPlayers, newNextPlayer)
    }

    companion object {
        private const val SPACE_COUNT = 10
        private const val WINNING_SCORE = 21

        private fun Player.move(distance: Int): Player {
            val newSpace = (space + distance) % SPACE_COUNT
            val newScore = score + newSpace + 1
            return Player(newSpace, newScore)
        }
    }
}

data class Player(val space: Int, val score: Int = 0)

fun Counter<GameState>.findOngoing(): List<Map.Entry<GameState, Long>> =
    entriesWithPositiveCount.filter { (state, _) -> !state.isFinished() }

fun main() {
    println(solve())
}
