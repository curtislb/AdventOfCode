package com.curtislb.adventofcode.year2019.day13.game.strategy

import com.curtislb.adventofcode.year2019.day13.game.Board
import com.curtislb.adventofcode.year2019.day13.game.Tile
import java.math.BigInteger
import kotlin.math.abs

/**
 * A strategy that selects the move which moves the closest paddle and ball closer together.
 */
object GreedyStrategy : Strategy {
    override fun nextMove(board: Board): BigInteger {
        var ballX: Int? = null
        var paddleX: Int? = null
        var minDistance = Int.MAX_VALUE

        // Find the closest ball and paddle on the board.
        val balls = board.findAll(Tile.BALL)
        val paddles = board.findAll(Tile.PADDLE)
        for (ball in balls) {
            for (paddle in paddles) {
                val distance = abs(ball.x - paddle.x)
                if (distance < minDistance) {
                    ballX = ball.x
                    paddleX = paddle.x
                    minDistance = distance
                }
            }
        }

        // Move the closest paddle towards the closest ball
        return when {
            ballX == null -> BigInteger.ZERO
            ballX < paddleX!! -> BigInteger("-1")
            ballX > paddleX -> BigInteger.ONE
            else -> BigInteger.ZERO
        }
    }
}
