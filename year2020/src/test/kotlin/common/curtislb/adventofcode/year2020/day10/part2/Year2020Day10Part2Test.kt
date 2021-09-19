package com.curtislb.adventofcode.year2020.day10.part2

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2020, day 10, part 2.
 */
class Year2020Day10Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(24803586664192L, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2020, day = 10, fileName = "test_input.txt"))
        assertEquals(19208L, solution)
    }
}
