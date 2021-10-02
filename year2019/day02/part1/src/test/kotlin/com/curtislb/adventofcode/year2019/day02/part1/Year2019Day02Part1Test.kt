package com.curtislb.adventofcode.year2019.day02.part1

import java.math.BigInteger
import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 2, part 1.
 */
class Year2019Day02Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(BigInteger("4138687"), solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(
            inputPath = Paths.get("..", "input", "test_input.txt"),
            nounValue = BigInteger.ONE,
            verbValue = BigInteger.ONE
        )
        assertEquals(solution, BigInteger("30"))
    }
}
