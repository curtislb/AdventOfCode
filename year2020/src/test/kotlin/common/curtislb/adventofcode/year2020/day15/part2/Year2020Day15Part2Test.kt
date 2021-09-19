package com.curtislb.adventofcode.year2020.day15.part2

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2020, day 15, part 2.
 */
class Year2020Day15Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(573522, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2020, day = 15, fileName = "test_input.txt"))
        assertEquals(175594, solution)
    }
}
