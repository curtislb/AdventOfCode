package com.curtislb.adventofcode.year2019.day03.part2

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for day 3, part 2.
 */
class TestSolution {
    @Test fun solveWithRealInput() {
        assertEquals(13836, solve())
    }

    @Test fun solveWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2019, day = 3, fileName = "test_input.txt"))
        assertEquals(410, solution)
    }
}
