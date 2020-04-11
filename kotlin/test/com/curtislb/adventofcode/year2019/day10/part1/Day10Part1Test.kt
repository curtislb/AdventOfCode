package com.curtislb.adventofcode.year2019.day10.part1

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for day 10, part 1.
 */
class Day10Part1Test {
    @Test fun testSolutionWithRealInput() {
        assertEquals(263, solve())
    }

    @Test fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2019, day = 10, fileName = "test_input.txt"))
        assertEquals(210, solution)
    }
}
