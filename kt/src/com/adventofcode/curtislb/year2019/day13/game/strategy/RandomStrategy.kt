package com.adventofcode.curtislb.year2019.day13.game.strategy

import com.adventofcode.curtislb.year2019.day13.game.Board
import java.math.BigInteger
import kotlin.random.Random
import kotlin.random.nextInt

/**
 * A [Strategy] that selects the player's next move at random among all possible moves.
 */
object RandomStrategy : Strategy {
    override fun nextMove(board: Board): BigInteger = Random.Default.nextInt(-1..1).toBigInteger()
}
