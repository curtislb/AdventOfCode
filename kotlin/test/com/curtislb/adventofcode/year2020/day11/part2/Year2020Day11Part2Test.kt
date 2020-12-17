package com.curtislb.adventofcode.year2020.day11.part2

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2020, day 11, part 2.
 */
class Year2020Day11Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(2234, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2020, day = 11, fileName = "test_input.txt"))
        assertEquals(26, solution)
    }
}
