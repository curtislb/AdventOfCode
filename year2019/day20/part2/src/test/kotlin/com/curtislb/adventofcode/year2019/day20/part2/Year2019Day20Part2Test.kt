package com.curtislb.adventofcode.year2019.day20.part2

import com.curtislb.adventofcode.common.io.pathToInput
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 20, part 2.
 */
class Year2019Day20Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(7798, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2019, day = 20, part = 2, fileName = "test_input.txt"))
        assertEquals(396, solution)
    }
}
