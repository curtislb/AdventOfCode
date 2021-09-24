package com.curtislb.adventofcode.year2019.day19.part2

import java.math.BigInteger
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

/**
 * Tests the solution to the puzzle for 2019, day 19, part 2.
 */
class Year2019Day19Part2Test {
    @Test
    fun testSolutionWithRealInput() {
        assertEquals(BigInteger("13530764"), solve())
    }
}
