package com.curtislb.adventofcode.year2020.day07.part1

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2020, day 7, part 1.
 */
class Year2020Day07Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(238, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "test_input.txt"))
        assertEquals(4, solution)
    }
}
