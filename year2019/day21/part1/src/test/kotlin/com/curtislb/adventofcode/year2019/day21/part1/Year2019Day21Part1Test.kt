package com.curtislb.adventofcode.year2019.day21.part1

import java.math.BigInteger
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 21, part 1.
 */
class Year2019Day21Part1Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(BigInteger("19357507"), solve())
    }
}
