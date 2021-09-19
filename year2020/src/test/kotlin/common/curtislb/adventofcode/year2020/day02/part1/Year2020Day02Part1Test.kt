package com.curtislb.adventofcode.year2020.day02.part1

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2020, day 2, part 1.
 */
class Year2020Day02Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(458, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2020, day = 2, fileName = "test_input.txt"))
        assertEquals(2, solution)
    }
}
