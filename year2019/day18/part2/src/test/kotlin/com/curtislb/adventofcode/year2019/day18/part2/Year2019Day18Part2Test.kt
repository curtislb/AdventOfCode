package com.curtislb.adventofcode.year2019.day18.part2

import com.curtislb.adventofcode.common.io.pathToInput
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 18, part 2.
 */
class Year2019Day18Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(1732L, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2019, day = 18, part = 2, fileName = "test_input.txt"))
        assertEquals(72L, solution)
    }
}
