package com.curtislb.adventofcode.year2019.day22.part1

import java.math.BigInteger
import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 22, part 1.
 */
class Year2019Day22Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(BigInteger("8191"), solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(
            inputPath = Paths.get("..", "input", "test_input.txt"),
            deckSize = BigInteger.TEN,
            targetCard = BigInteger.TWO
        )
        assertEquals(BigInteger.ONE, solution)
    }
}
