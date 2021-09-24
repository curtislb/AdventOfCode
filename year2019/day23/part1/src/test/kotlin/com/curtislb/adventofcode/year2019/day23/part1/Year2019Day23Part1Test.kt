package com.curtislb.adventofcode.year2019.day23.part1

import java.math.BigInteger
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 23, part 1.
 */
class Year2019Day23Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(BigInteger("18513"), solve())
    }
}
