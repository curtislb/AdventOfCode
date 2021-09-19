package com.curtislb.adventofcode.year2019.day21.part2

import org.junit.jupiter.api.Test
import java.math.BigInteger
import kotlin.test.assertEquals

/**
 * Tests the solution to the puzzle for 2019, day 21, part 2.
 */
class Year2019Day21Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(BigInteger("1142830249"), solve())
    }
}
