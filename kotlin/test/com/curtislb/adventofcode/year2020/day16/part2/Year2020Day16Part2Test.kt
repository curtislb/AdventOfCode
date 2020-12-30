package com.curtislb.adventofcode.year2020.day16.part2

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2020, day 16, part 2.
 */
class Year2020Day16Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(1069784384303L, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(
            inputPath = pathToInput(year = 2020, day = 16, part = 2, fileName = "test_input.txt"),
            fieldRegex = Regex("""[a-z]{4,5}""")
        )
        assertEquals(156L, solution)
    }
}
