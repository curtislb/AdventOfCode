package com.curtislb.adventofcode.year2020.day14.part2

import com.curtislb.adventofcode.common.io.pathToInput
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2020, day 14, part 2.
 */
class Year2020Day14Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(3278997609887L, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2020, day = 14, part = 2, fileName = "test_input.txt"))
        assertEquals(208, solution)
    }
}
