package com.curtislb.adventofcode.year2019.day04.part1

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2019, day 4, part 1.
 */
class Year2019Day04Part1Test {
    @Test fun testSolutionWithRealInput() {
        assertEquals(460, solve())
    }

    @Test fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2019, day = 4, fileName = "test_input.txt"))
        assertEquals(55, solution)
    }
}
