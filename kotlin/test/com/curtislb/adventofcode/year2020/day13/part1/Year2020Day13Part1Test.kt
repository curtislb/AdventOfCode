package com.curtislb.adventofcode.year2020.day13.part1

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2020, day 13, part 1.
 */
class Year2020Day13Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(4207, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2020, day = 13, fileName = "test_input.txt"))
        assertEquals(295, solution)
    }
}
