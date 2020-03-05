package com.curtislb.adventofcode.year2019.day19.part2

import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for day 19, part 2.
 */
class TestSolution {
    @Test fun solveWithRealInput() {
        assertEquals(BigInteger("13530764"), solve())
    }
}
