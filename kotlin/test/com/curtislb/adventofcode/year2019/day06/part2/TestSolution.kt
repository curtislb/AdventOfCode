package com.curtislb.adventofcode.year2019.day06.part2

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for day 6, part 2.
 */
class TestSolution {
    @Test fun solveWithRealInput() {
        assertEquals(499, solve())
    }

    @Test fun solveWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2019, day = 6, part = 2, fileName = "test_input.txt"))
        assertEquals(4, solution)
    }
}
