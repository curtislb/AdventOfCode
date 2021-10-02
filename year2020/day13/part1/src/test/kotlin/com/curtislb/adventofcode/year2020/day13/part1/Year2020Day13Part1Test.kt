package com.curtislb.adventofcode.year2020.day13.part1

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2020, day 13, part 1.
 */
class Year2020Day13Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(4207L, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "test_input.txt"))
        assertEquals(295L, solution)
    }
}
