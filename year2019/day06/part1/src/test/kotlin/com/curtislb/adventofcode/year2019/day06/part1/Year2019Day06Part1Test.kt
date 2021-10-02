package com.curtislb.adventofcode.year2019.day06.part1

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 6, part 1.
 */
class Year2019Day06Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(253104, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "part1", "test_input.txt"))
        assertEquals(42, solution)
    }
}
