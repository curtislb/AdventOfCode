package com.curtislb.adventofcode.year2019.day22.part2

import com.curtislb.adventofcode.common.io.pathToInput
import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2019, day 22, part 2.
 */
class Year2019Day22Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(BigInteger("1644352419829"), solve())
    }

    @Test
    fun testSolutionWithTestInput() {
        val solution = solve(
            inputPath = pathToInput(year = 2019, day = 22, fileName = "test_input.txt"),
            deckSize = BigInteger("11"),
            shuffleCount = BigInteger.TWO,
            targetPosition = BigInteger.ONE
        )
        assertEquals(BigInteger.TWO, solution)
    }
}
