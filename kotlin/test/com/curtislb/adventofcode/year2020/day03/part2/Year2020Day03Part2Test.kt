package com.curtislb.adventofcode.year2020.day03.part2

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2020, day 3, part 2.
 */
class Year2020Day03Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(2698900776L, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2020, day = 3, fileName = "test_input.txt"))
        assertEquals(336L, solution)
    }
}
