package com.curtislb.adventofcode.year2019.day07.part2

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for day 7, part 2.
 */
class TestSolution {
    @Test fun solveWithRealInput() {
        assertEquals(BigInteger("19741286"), solve())
    }

    @Test fun solveWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2019, day = 7, part = 2, fileName = "test_input.txt"))
        assertEquals(BigInteger("18216"), solution)
    }
}
