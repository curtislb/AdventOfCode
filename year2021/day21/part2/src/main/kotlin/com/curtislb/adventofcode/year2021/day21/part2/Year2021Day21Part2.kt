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

import com.curtislb.adventofcode.year2021.day21.dice.DiceGameState
import com.curtislb.adventofcode.year2021.day21.dice.dirac.DiracDice
import com.curtislb.adventofcode.year2021.day21.dice.dirac.DiracDiceGame
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Returns the solution to the puzzle for 2021, day 21, part 2.
 *
 * @param inputPath The path to the input file for this puzzle.
 * @param diceCount The number of [DiracDice] a player must roll on their turn.
 * @param dieSidesCount The number of sides on each of the [DiracDice].
 * @param winningScore The minimum score a player needs to win the game.
 */
fun solve(
    inputPath: Path = Paths.get("..", "input", "input.txt"),
    diceCount: Int = 3,
    dieSidesCount: Int = 3,
    winningScore: Int = 21
): Long {
    // Count all possible winning states of the game
    val initialState = DiceGameState.fromFile(inputPath.toFile())
    val dice = DiracDice(diceCount, dieSidesCount)
    val game = DiracDiceGame(initialState, dice)
    val stateCounts = game.countPossibleEndStates(winningScore)

    // Count the universes in which each player wins
    val playerWinCounts = LongArray(initialState.players.size)
    for ((state, count) in stateCounts.entriesWithNonzeroCount) {
        playerWinCounts[state.getWinner(winningScore)] += count
    }

    return playerWinCounts.max()
}

fun main() {
    println(solve())
}
