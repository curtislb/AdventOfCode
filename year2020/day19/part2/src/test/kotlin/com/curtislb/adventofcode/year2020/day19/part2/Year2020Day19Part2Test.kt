package com.curtislb.adventofcode.year2020.day19.part2

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2020, day 19, part 2.
 */
class Year2020Day19Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(316, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "part2", "test_input.txt"))
        assertEquals(12, solution)
    }
}
