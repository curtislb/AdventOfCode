package com.curtislb.adventofcode.year2020.day19.part1

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2020, day 19, part 1.
 */
class Year2020Day19Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(208, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "part1", "test_input.txt"))
        assertEquals(2, solution)
    }
}
