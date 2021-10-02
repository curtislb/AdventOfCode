package com.curtislb.adventofcode.year2019.day03.part2

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 3, part 2.
 */
class Year2019Day03Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(13836, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = Paths.get("..", "input", "test_input.txt"))
        assertEquals(410, solution)
    }
}
