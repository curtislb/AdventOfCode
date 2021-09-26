package com.curtislb.adventofcode.year2020.day05.part1

import com.curtislb.adventofcode.common.io.pathToInput
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2020, day 5, part 1.
 */
class Year2020Day05Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(959, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2020, day = 5, fileName = "test_input.txt"))
        assertEquals(820, solution)
    }
}
