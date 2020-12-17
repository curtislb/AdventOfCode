package com.curtislb.adventofcode.year2020.day17.part1

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2020, day 17, part 1.
 */
class Year2020Day17Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(232, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2020, day = 17, fileName = "test_input.txt"))
        assertEquals(112, solution)
    }
}
