package com.curtislb.adventofcode.year2020.day08.part2

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2020, day 8, part 2.
 */
class Year2020Day08Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(640, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2020, day = 8, fileName = "test_input.txt"))
        assertEquals(8, solution)
    }
}
