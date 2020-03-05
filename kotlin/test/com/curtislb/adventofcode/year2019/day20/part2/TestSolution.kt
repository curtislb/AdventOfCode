package com.curtislb.adventofcode.year2019.day20.part2

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for day 20, part 2.
 */
class TestSolution {
    @Test fun solveWithRealInput() {
        assertEquals(7798, solve())
    }

    @Test fun solveWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2019, day = 20, part = 2, fileName = "test_input.txt"))
        assertEquals(396, solution)
    }
}
