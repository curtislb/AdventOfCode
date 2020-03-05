package com.curtislb.adventofcode.year2019.day17.part2

import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for day 17, part 2.
 */
class TestSolution {
    @Test fun solveWithRealInput() {
        assertEquals(BigInteger("1681189"), solve())
    }
}
