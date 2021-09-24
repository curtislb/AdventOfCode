package com.curtislb.adventofcode.year2019.day24.part2

import com.curtislb.adventofcode.common.io.pathToInput
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 24, part 2.
 */
class Year2019Day24Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(2120, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(
            inputPath = pathToInput(year = 2019, day = 24, fileName = "test_input.txt"),
            durationMinutes = 10
        )
        assertEquals(99, solution)
    }
}
