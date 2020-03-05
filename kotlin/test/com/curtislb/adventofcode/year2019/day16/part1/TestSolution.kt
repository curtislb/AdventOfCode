package com.curtislb.adventofcode.year2019.day16.part1

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for day 16, part 1.
 */
class TestSolution {
    @Test fun solveWithRealInput() {
        assertEquals("96136976", solve())
    }

    @Test fun solveWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2019, day = 16, part = 1, fileName = "test_input.txt"))
        assertEquals("52432133", solution)
    }
}
