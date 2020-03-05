package com.curtislb.adventofcode.year2019.day24.part2

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for day 24, part 2.
 */
class TestSolution {
    @Test fun solveWithRealInput() {
        assertEquals(2120, solve())
    }

    @Test fun solveWithTestInput() {
        val solution = solve(
            inputPath = pathToInput(year = 2019, day = 24, fileName = "test_input.txt"),
            durationMinutes = 10
        )
        assertEquals(99, solution)
    }
}
