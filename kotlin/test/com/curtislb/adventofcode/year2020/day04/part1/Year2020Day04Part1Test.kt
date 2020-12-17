package com.curtislb.adventofcode.year2020.day04.part1

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2020, day 4, part 1.
 */
class Year2020Day04Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(190, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2020, day = 4, fileName = "test_input.txt"))
        assertEquals(2, solution)
    }
}
