package com.curtislb.adventofcode.year2019.day21.part1

import org.junit.Test
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for day 21, part 1.
 */
class Day21Part1Test {
    @Test fun testSolutionWithRealInput() {
        assertEquals(BigInteger("19357507"), solve())
    }
}