package com.curtislb.adventofcode.year2020.day02.part2

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2020, day 2, part 2.
 */
class Year2020Day02Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(342, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "test_input.txt"))
        assertEquals(1, solution)
    }
}
