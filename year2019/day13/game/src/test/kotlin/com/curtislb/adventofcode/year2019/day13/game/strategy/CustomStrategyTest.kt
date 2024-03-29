package com.curtislb.adventofcode.year2019.day13.game.strategy

import com.curtislb.adventofcode.common.geometry.Point
import com.curtislb.adventofcode.year2019.day13.game.Board
import com.curtislb.adventofcode.year2019.day13.game.Tile
import java.math.BigInteger
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests [CustomStrategy].
 */
class CustomStrategyTest {
    @Test
    fun testNextMove() {
        val strategy = CustomStrategy { board ->
            when (board[Point.ORIGIN]) {
                Tile.EMPTY -> {
                    board[Point.ORIGIN] = Tile.WALL
                    BigInteger.TWO
                }

                Tile.WALL -> {
                    board[Point.ORIGIN] = Tile.BALL
                    BigInteger.ONE
                }

                else -> BigInteger.ZERO
            }
        }
        val board = Board()
        assertEquals(BigInteger.TWO, strategy.nextMove(board))
        assertEquals(BigInteger.ONE, strategy.nextMove(board))
        assertEquals(BigInteger.ZERO, strategy.nextMove(board))
    }
}
