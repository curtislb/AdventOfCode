package com.curtislb.adventofcode.year2019.day01.part1

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 1, part 1.
 */
class Year2019Day01Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(3384232, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "test_input.txt"))
        assertEquals(33583, solution)
    }
}
