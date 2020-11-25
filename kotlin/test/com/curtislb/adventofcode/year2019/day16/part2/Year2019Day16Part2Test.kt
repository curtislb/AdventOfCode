package com.curtislb.adventofcode.year2019.day16.part2

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2019, day 16, part 2.
 */
class Year2019Day16Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals("85600369", solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2019, day = 16, part = 2, fileName = "test_input.txt"))
        assertEquals("53553731", solution)
    }
}
