package com.curtislb.adventofcode.year2021.day14.part2

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2021, day 14, part 2.
 */
class Year2021Day14Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(7477815755570L, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "test_input.txt"))
        assertEquals(2188189693529L, solution)
    }
}
