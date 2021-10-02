package com.curtislb.adventofcode.year2019.day22.part2

import java.math.BigInteger
import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 22, part 2.
 */
class Year2019Day22Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(BigInteger("1644352419829"), solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(
            inputPath = Paths.get("..", "input", "test_input.txt"),
            deckSize = BigInteger("11"),
            shuffleCount = BigInteger.TWO,
            targetPosition = BigInteger.ONE
        )
        assertEquals(BigInteger.TWO, solution)
    }
}
