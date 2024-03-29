package com.curtislb.adventofcode.year2019.day05.part1

import java.math.BigInteger
import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 5, part 1.
 */
class Year2019Day05Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(BigInteger("5044655"), solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(
            inputPath = Paths.get("..", "input", "part1", "test_input.txt"),
            systemId = BigInteger("4")
        )
        assertEquals(BigInteger("42"), solution)
    }
}
