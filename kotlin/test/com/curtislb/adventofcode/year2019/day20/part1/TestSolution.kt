package com.curtislb.adventofcode.year2019.day20.part1

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for day 20, part 1.
 */
class TestSolution {
    @Test fun solveWithRealInput() {
        assertEquals(644, solve())
    }

    @Test fun solveWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2019, day = 20, part = 1, fileName = "test_input.txt"))
        assertEquals(58, solution)
    }
}
