package com.curtislb.adventofcode.year2020.day06.part2

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2020, day 6, part 2.
 */
class Year2020Day06Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(3316, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2020, day = 6, fileName = "test_input.txt"))
        assertEquals(6, solution)
    }
}
