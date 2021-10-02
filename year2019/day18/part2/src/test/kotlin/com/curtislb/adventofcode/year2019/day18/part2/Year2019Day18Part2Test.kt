package com.curtislb.adventofcode.year2019.day18.part2

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 18, part 2.
 */
class Year2019Day18Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(1732L, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "part2", "test_input.txt"))
        assertEquals(72L, solution)
    }
}
