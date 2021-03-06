package com.curtislb.adventofcode.year2019.day05.part1

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2019, day 5, part 1.
 */
class Year2019Day05Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(BigInteger("5044655"), solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(
            inputPath = pathToInput(year = 2019, day = 5, part = 1, fileName = "test_input.txt"),
            systemId = BigInteger("4")
        )
        assertEquals(BigInteger("42"), solution)
    }
}
