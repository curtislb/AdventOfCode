package com.curtislb.adventofcode.year2019.day13.game.strategy

import com.curtislb.adventofcode.year2019.day13.game.Board
import java.math.BigInteger

/**
 * A strategy that uses a custom [process] function to select each move.
 *
 * @param process Returns an integer representing the next move that the player should make, based on the board state.
 */
class CustomStrategy(private val process: (board: Board) -> BigInteger) : Strategy {
    override fun nextMove(board: Board): BigInteger = process(board)
}
