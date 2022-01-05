package com.curtislb.adventofcode.year2021.day16.part2

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2021, day 16, part 2.
 */
class Year2021Day16Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(911945136934L, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "part2", "test_input.txt"))
        assertEquals(1L, solution)
    }
}
