package com.curtislb.adventofcode.year2020.day25.part1

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2020, day 25, part 1.
 */
class Year2020Day25Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(42668, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "test_input.txt"))
        assertEquals(14897079, solution)
    }
}
