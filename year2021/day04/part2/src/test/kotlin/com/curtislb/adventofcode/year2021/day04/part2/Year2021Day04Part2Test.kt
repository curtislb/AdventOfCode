package com.curtislb.adventofcode.year2021.day04.part2

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2021, day 04, part 2.
 */
class Year2021Day04Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(13158, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "test_input.txt"))
        assertEquals(1924, solution)
    }
}
