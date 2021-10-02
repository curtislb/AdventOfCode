package com.curtislb.adventofcode.year2020.day21.part2

import java.nio.file.Paths
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

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
        val solution = solve(inputPath = Paths.get("..", "input", "test_input.txt"))
        assertEquals("mxmxvkd,sqjhc,fvjkl", solution)
    }
}
