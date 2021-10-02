package com.curtislb.adventofcode.year2019.day14.part2

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 14, part 2.
 */
class Year2019Day14Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(2169535L, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "test_input.txt"))
        assertEquals(460664L, solution)
    }
}
