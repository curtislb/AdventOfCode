package com.curtislb.adventofcode.year2019.day12.part1

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for day 12, part 1.
 */
class TestSolution {
    @Test fun solveWithRealInput() {
        assertEquals(7636, solve())
    }

    @Test fun solveWithTestInput() {
        val solution = solve(
            inputPath = pathToInput(year = 2019, day = 12, fileName = "test_input.txt"),
            stepCount = 100
        )
        assertEquals(1940, solution)
    }
}
