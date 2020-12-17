package com.curtislb.adventofcode.year2020.day02.part2

import com.curtislb.adventofcode.common.io.pathToInput
import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * Tests the solution to the puzzle for 2020, day 2, part 2.
 */
class Year2020Day02Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(342, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2020, day = 2, fileName = "test_input.txt"))
        assertEquals(1, solution)
    }
}
