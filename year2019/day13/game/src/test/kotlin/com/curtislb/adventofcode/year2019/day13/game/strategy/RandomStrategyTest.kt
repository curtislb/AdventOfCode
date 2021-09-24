package com.curtislb.adventofcode.year2019.day13.game.strategy

import com.curtislb.adventofcode.year2019.day13.game.Board
import java.math.BigInteger
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test

/**
 * Tests [RandomStrategy].
 */
class RandomStrategyTest {
    @Test
    fun testNextMove() {
        val board = Board()
        val validMoves = setOf(BigInteger("-1"), BigInteger.ZERO, BigInteger.ONE)
        for (i in 1..20) {
            assertTrue(RandomStrategy.nextMove(board) in validMoves)
        }
    }
}
