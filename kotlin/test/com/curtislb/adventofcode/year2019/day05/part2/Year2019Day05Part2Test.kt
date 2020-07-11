package com.curtislb.adventofcode.year2019.day05.part2

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2019, day 5, part 2.
 */
class Year2019Day05Part2Test {
    @Test fun testSolutionWithRealInput() {
        assertEquals(BigInteger("7408802"), solve())
    }

    @Test fun testSolutionWithTestInput() {
        val solution = solve(
            inputPath = pathToInput(year = 2019, day = 5, part = 2, fileName = "test_input.txt"),
            systemId = BigInteger("8")
        )
        assertEquals(BigInteger("1000"), solution)
    }
}
