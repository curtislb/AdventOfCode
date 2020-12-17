package com.curtislb.adventofcode.year2020.day09.part1

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2020, day 9, part 1.
 */
class Year2020Day09Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(22406676L, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(
            inputPath = pathToInput(year = 2020, day = 9, fileName = "test_input.txt"),
            preambleSize = 5
        )
        assertEquals(127L, solution)
    }
}
