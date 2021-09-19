package com.curtislb.adventofcode.year2020.day25.part1

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2020, day 25, part 1.
 */
class Year2020Day25Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(42668, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2020, day = 25, fileName = "test_input.txt"))
        assertEquals(14897079, solution)
    }
}
