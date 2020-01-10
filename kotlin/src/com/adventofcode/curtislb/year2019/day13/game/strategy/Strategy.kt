package com.adventofcode.curtislb.year2019.day13.game.strategy

import com.adventofcode.curtislb.year2019.day13.game.Board
import java.math.BigInteger

/**
 * A method for selecting the player's next input, given the current state of the [Board].
 */
interface Strategy {
    /**
     * Selects a player's next move, based on the current [Board] state.
     * @param board The current state of the game [Board].
     * @return A [BigInteger] value representing the next move to be made by the player.
     */
    fun nextMove(board: Board): BigInteger
}
