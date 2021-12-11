package com.curtislb.adventofcode.year2021.day06.part1

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2021, day 06, part 1.
 */
class Year2021Day06Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(390923L, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "test_input.txt"))
        assertEquals(5934L, solution)
    }
}
