package com.curtislb.adventofcode.year2019.day02.part1

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for day 2, part 1.
 */
class TestSolution {
    @Test fun solveWithRealInput() {
        assertEquals(BigInteger("4138687"), solve())
    }

    @Test fun solveWithTestInput() {
        val solution = solve(
            inputPath = pathToInput(year = 2019, day = 2, fileName = "test_input.txt"),
            nounValue = BigInteger.ONE,
            verbValue = BigInteger.ONE
        )
        assertEquals(solution, BigInteger("30"))
    }
}
