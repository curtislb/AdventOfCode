package com.curtislb.adventofcode.year2019.day02.part2

import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2019, day 2, part 2.
 */
class Year2019Day02Part2Test {
    @Test fun testSolutionWithRealInput() {
        assertEquals(BigInteger("6635"), solve())
    }
}
