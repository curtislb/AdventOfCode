package com.curtislb.adventofcode.year2020.day18.part1

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2020, day 18, part 1.
 */
class Year2020Day18Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(1890866893020L, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2020, day = 18, fileName = "test_input.txt"))
        assertEquals(26457L, solution)
    }
}
