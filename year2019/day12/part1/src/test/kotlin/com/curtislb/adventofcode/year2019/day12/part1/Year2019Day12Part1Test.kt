package com.curtislb.adventofcode.year2019.day12.part1

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 12, part 1.
 */
class Year2019Day12Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(7636, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution =
            solve(inputPath = Paths.get("..", "input", "test_input.txt"), stepCount = 100)
        assertEquals(1940, solution)
    }
}
