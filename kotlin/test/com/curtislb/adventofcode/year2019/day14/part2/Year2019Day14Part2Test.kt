package com.curtislb.adventofcode.year2019.day14.part2

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2019, day 14, part 2.
 */
class Year2019Day14Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(2169535L, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2019, day = 14, fileName = "test_input.txt"))
        assertEquals(460664L, solution)
    }
}
