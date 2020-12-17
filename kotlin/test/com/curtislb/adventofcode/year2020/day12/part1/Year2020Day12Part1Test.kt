package com.curtislb.adventofcode.year2020.day12.part1

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2020, day 12, part 1.
 */
class Year2020Day12Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(2847, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2020, day = 12, fileName = "test_input.txt"))
        assertEquals(25, solution)
    }
}
