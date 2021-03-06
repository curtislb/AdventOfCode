package com.curtislb.adventofcode.year2019.day09.part1

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2019, day 9, part 1.
 */
class Year2019Day09Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(BigInteger("3780860499"), solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2019, day = 9, fileName = "test_input.txt"))
        assertEquals(BigInteger("1219070632396864"), solution)
    }
}
