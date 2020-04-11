package com.curtislb.adventofcode.year2019.day23.part1

import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for day 23, part 1.
 */
class Day23Part1Test {
    @Test fun testSolutionWithRealInput() {
        assertEquals(BigInteger("18513"), solve())
    }
}
