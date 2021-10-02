package com.curtislb.adventofcode.year2019.day03.part1

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 3, part 1.
 */
class Year2019Day03Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(709, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "test_input.txt"))
        assertEquals(135, solution)
    }
}
