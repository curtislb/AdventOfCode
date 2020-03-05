package com.curtislb.adventofcode.year2019.day06.part1

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for day 6, part 1.
 */
class TestSolution {
    @Test fun solveWithRealInput() {
        assertEquals(253104, solve())
    }

    @Test fun solveWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2019, day = 6, part = 1, fileName = "test_input.txt"))
        assertEquals(42, solution)
    }
}
