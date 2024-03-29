package com.curtislb.adventofcode.year2019.day13.game.strategy

import com.curtislb.adventofcode.year2019.day13.game.Board
import java.io.InputStream
import java.io.PrintStream
import java.math.BigInteger
import kotlin.test.assertEquals
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Tests [InteractiveStrategy].
 */
class InteractiveStrategyTest {
    private lateinit var standardInput: InputStream
    private lateinit var standardOutput: PrintStream

    @BeforeEach
    fun setUp() {
        standardInput = System.`in`
        standardOutput = System.out
    }

    @AfterEach
    fun tearDown() {
        System.setIn(standardInput)
        System.setOut(standardOutput)
    }

    @Test
    fun testNextMove() {
        System.setIn(
            """
                0
                1
                -1
                
                31
                -172
            """.trimIndent().byteInputStream()
        )
        System.setOut(PrintStream(PrintStream.nullOutputStream()))
        val board = Board()
        assertEquals(BigInteger.ZERO, InteractiveStrategy.nextMove(board))
        assertEquals(BigInteger.ONE, InteractiveStrategy.nextMove(board))
        assertEquals(BigInteger("-1"), InteractiveStrategy.nextMove(board))
        assertEquals(BigInteger.ZERO, InteractiveStrategy.nextMove(board))
        assertEquals(BigInteger("31"), InteractiveStrategy.nextMove(board))
        assertEquals(BigInteger("-172"), InteractiveStrategy.nextMove(board))
    }
}
