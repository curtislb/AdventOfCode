package com.curtislb.adventofcode.year2020.day09.part1

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2020, day 9, part 1.
 */
class Year2020Day09Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(22406676L, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution =
            solve(inputPath = Paths.get("..", "input", "test_input.txt"), preambleSize = 5)
        assertEquals(127L, solution)
    }
}
