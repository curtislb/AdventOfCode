package com.curtislb.adventofcode.year2020.day19.part1

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2020, day 19, part 1.
 */
class Year2020Day19Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(208, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2020, day = 19, part = 1, fileName = "test_input.txt"))
        assertEquals(2, solution)
    }
}
