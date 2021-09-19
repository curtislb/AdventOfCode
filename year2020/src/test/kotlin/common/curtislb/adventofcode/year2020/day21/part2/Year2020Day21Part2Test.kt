package com.curtislb.adventofcode.year2020.day21.part2

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2020, day 21, part 2.
 */
class Year2020Day21Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals("fllssz,kgbzf,zcdcdf,pzmg,kpsdtv,fvvrc,dqbjj,qpxhfp", solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2020, day = 21, fileName = "test_input.txt"))
        assertEquals("mxmxvkd,sqjhc,fvjkl", solution)
    }
}
