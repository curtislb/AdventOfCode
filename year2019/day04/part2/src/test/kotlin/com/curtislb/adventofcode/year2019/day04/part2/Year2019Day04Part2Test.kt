package com.curtislb.adventofcode.year2019.day04.part2

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 4, part 2.
 */
class Year2019Day04Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(290, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "test_input.txt"))
        assertEquals(16, solution)
    }
}
