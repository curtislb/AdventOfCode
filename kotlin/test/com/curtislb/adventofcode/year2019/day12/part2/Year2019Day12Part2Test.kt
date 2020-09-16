package com.curtislb.adventofcode.year2019.day12.part2

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2019, day 12, part 2.
 */
class Year2019Day12Part2Test {
    @Test fun testSolutionWithRealInput() {
        assertEquals(281691380235984L, solve())
    }

    @Test fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2019, day = 12, fileName = "test_input.txt"))
        assertEquals(4686774924L, solution)
    }
}
