package com.curtislb.adventofcode.year2019.day04.part2

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for day 4, part 2.
 */
class TestSolution {
    @Test fun solveWithRealInput() {
        assertEquals(290, solve())
    }

    @Test fun solveWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2019, day = 4, fileName = "test_input.txt"))
        assertEquals(16, solution)
    }
}
