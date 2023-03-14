package com.curtislb.adventofcode.year2021.day21.dice.dirac

import com.curtislb.adventofcode.common.collection.Counter
import com.curtislb.adventofcode.year2021.day21.dice.DiceGameState

/**
 * A game where players take turns rolling a set of [DiracDice] and moving a number of spaces equal
 * to the total rolled in all possible universes.
 *
 * @param initialState The initial state of the game.
 * @param dice The dice that all players will take turns rolling.
 */
class DiracDiceGame(private val initialState: DiceGameState, private val dice: DiracDice) {
    /**
     * Returns a [Counter] of all reachable end states by playing the game until any player's score
     * equals or exceeds the given [winningScore].
     */
    fun countPossibleEndStates(winningScore: Int): Counter<DiceGameState> {
        // Populate a counter with possible game states
        val stateCounter = Counter<DiceGameState>().apply { this[initialState]++ }
        var ongoingGameEntries = stateCounter.entries.toList()
        while (ongoingGameEntries.isNotEmpty()) {
            // Advance all ongoing games by one turn and adjust counts accordingly
            for ((state, stateCount) in ongoingGameEntries) {
                stateCounter[state] -= stateCount
                for (rollResult in dice.possibleResults) {
                    val newState = state.nextTurn(rollResult)
                    val resultCount = dice.countOutcomes(rollResult)
                    stateCounter[newState] += stateCount * resultCount
                }
            }

            // Remove games where a player has won from the set of ongoing games
            ongoingGameEntries = stateCounter.entries.filter { (state, _) ->
                state.getWinner(winningScore) == DiceGameState.NO_WINNER
            }
        }

        return stateCounter
    }
}
