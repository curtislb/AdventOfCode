package com.curtislb.adventofcode.year2019.day07.part1

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for day 7, part 1.
 */
class TestSolution {
    @Test fun solveWithRealInput() {
        assertEquals(BigInteger("272368"), solve())
    }

    @Test fun solveWithTestInput() {
        val solution = solve(inputPath = pathToInput(year = 2019, day = 7, part = 1, fileName = "test_input.txt"))
        assertEquals(BigInteger("65210"), solution)
    }
}
