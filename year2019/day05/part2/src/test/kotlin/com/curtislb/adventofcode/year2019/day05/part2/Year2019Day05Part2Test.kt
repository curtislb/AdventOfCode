package com.curtislb.adventofcode.year2019.day05.part2

import java.math.BigInteger
import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 5, part 2.
 */
class Year2019Day05Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(BigInteger("7408802"), solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(
            inputPath = Paths.get("..", "input", "part2", "test_input.txt"),
            systemId = BigInteger("8")
        )
        assertEquals(BigInteger("1000"), solution)
    }
}
