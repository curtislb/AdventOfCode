package com.curtislb.adventofcode.year2019.day23.part2

import java.math.BigInteger
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 23, part 2.
 */
class Year2019Day23Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(BigInteger("13286"), solve())
    }
}
