package com.curtislb.adventofcode.year2019.day10.part2

import com.curtislb.adventofcode.common.io.pathToInput
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 10, part 2.
 */
class Year2019Day10Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(1110, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(
            inputPath = pathToInput(year = 2019, day = 10, fileName = "test_input.txt"),
            targetNumber = 199
        )
        assertEquals(906, solution)
    }
}
