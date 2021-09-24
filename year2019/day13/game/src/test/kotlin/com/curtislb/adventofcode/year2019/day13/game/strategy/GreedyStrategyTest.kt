package com.curtislb.adventofcode.year2019.day13.game.strategy

import com.curtislb.adventofcode.common.grid.Point
import com.curtislb.adventofcode.year2019.day13.game.Board
import com.curtislb.adventofcode.year2019.day13.game.Tile
import java.math.BigInteger
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests [GreedyStrategy].
 */
class GreedyStrategyTest {
    @Test
    fun testNextMoveWithNoBallOrPaddle() {
        assertEquals(BigInteger.ZERO, GreedyStrategy.nextMove(Board()))
    }

    @Test
    fun testNextMoveWithNoBall() {
        val board = Board()
        board[Point.ORIGIN] = Tile.PADDLE
        assertEquals(BigInteger.ZERO, GreedyStrategy.nextMove(board))

        board[Point(2, -3)] = Tile.PADDLE
        assertEquals(BigInteger.ZERO, GreedyStrategy.nextMove(board))

        board[Point(0, -1)] = Tile.PADDLE
        assertEquals(BigInteger.ZERO, GreedyStrategy.nextMove(board))
    }

    @Test
    fun testNextMoveWithNoPaddle() {
        val board = Board()
        board[Point(1, -4)] = Tile.BALL
        assertEquals(BigInteger.ZERO, GreedyStrategy.nextMove(board))

        board[Point.ORIGIN] = Tile.BALL
        assertEquals(BigInteger.ZERO, GreedyStrategy.nextMove(board))

        board[Point(0, -2)] = Tile.BALL
        assertEquals(BigInteger.ZERO, GreedyStrategy.nextMove(board))
    }

    @Test
    fun testNextMoveWithBallAndPaddle() {
        var board = Board()
        board[Point.ORIGIN] = Tile.BALL
        board[Point(1, 0)] = Tile.PADDLE
        assertEquals(BigInteger("-1"), GreedyStrategy.nextMove(board))

        board = Board()
        board[Point(3, -1)] = Tile.BALL
        board[Point(1, -4)] = Tile.PADDLE
        assertEquals(BigInteger.ONE, GreedyStrategy.nextMove(board))

        board = Board()
        board[Point(2, -5)] = Tile.BALL
        board[Point(2, -3)] = Tile.PADDLE
        assertEquals(BigInteger.ZERO, GreedyStrategy.nextMove(board))
    }

    @Test
    fun testNextMoveWithMultipleBallsAndPaddles() {
        var board = Board()
        board[Point(5, -5)] = Tile.BALL
        board[Point(8, 0)] = Tile.BALL
        board[Point(4, -1)] = Tile.BALL
        board[Point(6, -1)] = Tile.PADDLE
        board[Point(0, -8)] = Tile.PADDLE
        board[Point(2, -10)] = Tile.PADDLE
        assertEquals(BigInteger("-1"), GreedyStrategy.nextMove(board))

        board = Board()
        board[Point(1, -7)] = Tile.BALL
        board[Point(8, -1)] = Tile.BALL
        board[Point(6, -4)] = Tile.BALL
        board[Point(0, -9)] = Tile.PADDLE
        board[Point(3, -7)] = Tile.PADDLE
        board[Point(8, -3)] = Tile.PADDLE
        assertEquals(BigInteger.ZERO, GreedyStrategy.nextMove(board))

        board = Board()
        board[Point(12, -9)] = Tile.BALL
        board[Point(13, -1)] = Tile.BALL
        board[Point(1, -9)] = Tile.BALL
        board[Point(7, -3)] = Tile.PADDLE
        board[Point(6, -19)] = Tile.PADDLE
        board[Point(6, -12)] = Tile.PADDLE
        assertEquals(BigInteger.ONE, GreedyStrategy.nextMove(board))
    }
}
