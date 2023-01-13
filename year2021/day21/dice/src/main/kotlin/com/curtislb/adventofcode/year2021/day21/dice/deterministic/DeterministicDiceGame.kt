package com.curtislb.adventofcode.year2021.day21.dice.deterministic

import com.curtislb.adventofcode.year2021.day21.dice.DiceGameState

/**
 * A game where players take turns rolling a [DeterministicDie] a given number of times and moving a
 * number of spaces equal to the total rolled.
 *
 * @param initialState The initial state of the game.
 * @param die The die that all players will take turns rolling.
 * @param rollsPerTurn The number of times a player must roll the [die] on their turn.
 */
class DeterministicDiceGame(
    private val initialState: DiceGameState,
    private val die: DeterministicDie,
    private val rollsPerTurn: Int
) {
    /**
     * Returns the result of playing the game until any player's score equals or exceeds the given
     * [winningScore].
     */
    fun playToEnd(winningScore: Int): Result {
        var state = initialState
        var rollCount = 0
        while (state.getWinner(winningScore) == DiceGameState.NO_WINNER) {
            var rollResult = 0
            repeat(rollsPerTurn) { rollResult += die.getResult(rollCount + 1 + it) }
            rollCount += rollsPerTurn
            state = state.nextTurn(rollResult)
        }
        return Result(gameState = state, totalDieRolls = rollCount)
    }

    /**
     * A game result returned by [playToEnd].
     *
     * @param gameState The game state immediately after a player has won.
     * @param totalDieRolls The total number of times the die was rolled during the game.
     */
    data class Result(val gameState: DiceGameState, val totalDieRolls: Int)
}