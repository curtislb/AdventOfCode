package com.curtislb.adventofcode.year2021.day03.part1

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2021, day 03, part 1.
 */
class Year2021Day03Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(2743844, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "test_input.txt"))
        assertEquals(198, solution)
    }
}
