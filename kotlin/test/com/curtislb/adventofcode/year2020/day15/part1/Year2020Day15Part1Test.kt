package com.curtislb.adventofcode.year2020.day15.part1

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2020, day 15, part 1.
 */
class Year2020Day15Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(1294, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2020, day = 15, fileName = "test_input.txt"))
        assertEquals(436, solution)
    }
}
