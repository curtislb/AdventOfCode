package com.curtislb.adventofcode.year2019.day16.part1

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2019, day 16, part 1.
 */
class Year2019Day16Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals("96136976", solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2019, day = 16, part = 1, fileName = "test_input.txt"))
        assertEquals("52432133", solution)
    }
}
