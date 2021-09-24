package com.curtislb.adventofcode.year2019.day07.part2

import com.curtislb.adventofcode.common.io.pathToInput
import java.math.BigInteger
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 7, part 2.
 */
class Year2019Day07Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(BigInteger("19741286"), solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2019, day = 7, part = 2, fileName = "test_input.txt"))
        assertEquals(BigInteger("18216"), solution)
    }
}
