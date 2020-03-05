package com.curtislb.adventofcode.year2019.day14.part1

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for day 14, part 1.
 */
class TestSolution {
    @Test fun solveWithRealInput() {
        assertEquals(843220L, solve())
    }

    @Test fun solveWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2019, day = 14, fileName = "test_input.txt"))
        assertEquals(2210736L, solution)
    }
}
