package com.curtislb.adventofcode.year2019.day10.part2

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for day 10, part 2.
 */
class TestSolution {
    @Test fun solveWithRealInput() {
        assertEquals(1110, solve())
    }

    @Test fun solveWithTestInput() {
        val solution = solve(
            inputPath = pathToInput(year = 2019, day = 10, fileName = "test_input.txt"),
            targetNumber = 199
        )
        assertEquals(906, solution)
    }
}
