package com.curtislb.adventofcode.year2019.day09.part2

import java.math.BigInteger
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 9, part 2.
 */
class Year2019Day09Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(BigInteger("33343"), solve())
    }
}
