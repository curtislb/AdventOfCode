package com.curtislb.adventofcode.year2019.day21.part2

import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for day 21, part 2.
 */
class TestSolution {
    @Test fun solveWithRealInput() {
        assertEquals(BigInteger("1142830249"), solve())
    }
}
