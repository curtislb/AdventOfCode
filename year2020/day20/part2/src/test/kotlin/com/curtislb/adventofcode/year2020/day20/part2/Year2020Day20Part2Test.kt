package com.curtislb.adventofcode.year2020.day20.part2

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2020, day 20, part 2.
 */
class Year2020Day20Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(2161, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "test_input.txt"))
        assertEquals(273, solution)
    }
}
