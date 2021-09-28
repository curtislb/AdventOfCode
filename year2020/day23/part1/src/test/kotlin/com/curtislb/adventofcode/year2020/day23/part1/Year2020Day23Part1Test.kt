package com.curtislb.adventofcode.year2020.day23.part1

import com.curtislb.adventofcode.common.io.pathToInput
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2020, day 23, part 1.
 */
class Year2020Day23Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(52937846, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2020, day = 23, fileName = "test_input.txt"))
        assertEquals(67384529, solution)
    }
}
