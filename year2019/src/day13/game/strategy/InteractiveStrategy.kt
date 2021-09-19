package com.curtislb.adventofcode.year2019.day13.game.strategy

import com.curtislb.adventofcode.year2019.day13.game.Board
import java.math.BigInteger

/**
 * A strategy that prints the current board state and waits for the player to select a move via standard input.
 */
object InteractiveStrategy : Strategy {
    override fun nextMove(board: Board): BigInteger {
        println(board)
        print("Input (default = 0): ")
        val input = readLine()
        return if (input.isNullOrEmpty()) BigInteger.ZERO else BigInteger(input)
    }
}
