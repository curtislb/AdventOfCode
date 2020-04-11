package com.curtislb.adventofcode.year2019.day22.part1

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for day 22, part 1.
 */
class Day22Part1Test {
    @Test fun testSolutionWithRealInput() {
        assertEquals(BigInteger("8191"), solve())
    }

    @Test fun testSolutionWithTestInput() {
        val solution = solve(
            inputPath = pathToInput(year = 2019, day = 22, fileName = "test_input.txt"),
            deckSize = BigInteger.TEN,
            targetCard = BigInteger.TWO
        )
        assertEquals(BigInteger.ONE, solution)
    }
}
