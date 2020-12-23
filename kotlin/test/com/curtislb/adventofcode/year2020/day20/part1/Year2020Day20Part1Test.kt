package com.curtislb.adventofcode.year2020.day20.part1

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2020, day 20, part 1.
 */
class Year2020Day20Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(14986175499719L, solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2020, day = 20, fileName = "test_input.txt"))
        assertEquals(20899048083289L, solution)
    }
}
