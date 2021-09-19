package com.curtislb.adventofcode.year2020.day10.part1

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2020, day 10, part 1.
 */
class Year2020Day10Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(2170L, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2020, day = 10, fileName = "test_input.txt"))
        assertEquals(220L, solution)
    }
}
