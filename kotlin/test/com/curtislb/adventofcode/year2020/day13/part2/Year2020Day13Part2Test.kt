package com.curtislb.adventofcode.year2020.day13.part2

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2020, day 13, part 2.
 */
class Year2020Day13Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(725850285300475L, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2020, day = 13, fileName = "test_input.txt"))
        assertEquals(1068781L, solution)
    }
}
