package com.curtislb.adventofcode.year2020.day22.part1

import com.curtislb.adventofcode.common.io.pathToInput
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2020, day 22, part 1.
 */
class Year2020Day22Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(33403, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2020, day = 22, fileName = "test_input.txt"))
        assertEquals(306, solution)
    }
}
