package com.curtislb.adventofcode.year2021.day10.part2

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2021, day 10, part 2.
 */
class Year2021Day10Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(1605968119L, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "test_input.txt"))
        assertEquals(288957L, solution)
    }
}
