package com.curtislb.adventofcode.year2019.day03.part1

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2019, day 3, part 1.
 */
class Year2019Day03Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(709, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2019, day = 3, fileName = "test_input.txt"))
        assertEquals(135, solution)
    }
}
