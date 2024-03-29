package com.curtislb.adventofcode.year2020.day18.part2

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2020, day 18, part 2.
 */
class Year2020Day18Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(34646237037193L, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "test_input.txt"))
        assertEquals(694173L, solution)
    }
}
