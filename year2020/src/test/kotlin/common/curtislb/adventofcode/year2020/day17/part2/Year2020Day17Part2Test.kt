package com.curtislb.adventofcode.year2020.day17.part2

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2020, day 17, part 2.
 */
class Year2020Day17Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(1620, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2020, day = 17, fileName = "test_input.txt"))
        assertEquals(848, solution)
    }
}
